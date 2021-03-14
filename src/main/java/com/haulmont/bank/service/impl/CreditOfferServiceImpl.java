package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.create.CreditOfferCreateDto;
import com.haulmont.bank.data.dto.get.CreditOfferGetDto;
import com.haulmont.bank.data.dto.update.CreditOfferUpdateDto;
import com.haulmont.bank.data.mapstruct.CreditOfferMapper;
import com.haulmont.bank.data.model.Client;
import com.haulmont.bank.data.model.Credit;
import com.haulmont.bank.data.model.CreditOffer;
import com.haulmont.bank.data.model.PaymentSchedule;
import com.haulmont.bank.data.repository.ClientRepository;
import com.haulmont.bank.data.repository.CreditOfferRepository;
import com.haulmont.bank.data.repository.CreditRepository;
import com.haulmont.bank.data.repository.PaymentScheduleRepository;
import com.haulmont.bank.service.ICreditOfferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CreditOfferServiceImpl implements ICreditOfferService {

    private final CreditOfferRepository creditOfferRepository;
    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final PaymentScheduleRepository paymentScheduleRepository;
    private final CreditOfferMapper creditOfferMapper;

    public CreditOfferServiceImpl(CreditOfferRepository creditOfferRepository,
                                  ClientRepository clientRepository,
                                  CreditRepository creditRepository,
                                  PaymentScheduleRepository paymentScheduleRepository,
                                  CreditOfferMapper creditOfferMapper) {
        this.creditOfferRepository = creditOfferRepository;
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
        this.paymentScheduleRepository = paymentScheduleRepository;
        this.creditOfferMapper = creditOfferMapper;
    }

    @Override
    @Transactional
    public CreditOfferGetDto createCreditOffer(CreditOfferCreateDto creditOfferCreateDto) {
        final Client client = clientRepository.findById(creditOfferCreateDto.getClientId()).orElseThrow(NullPointerException::new);
        final Credit credit = creditRepository.findById(creditOfferCreateDto.getCreditId()).orElseThrow(NullPointerException::new);
        client.getCredits().add(credit);
        clientRepository.saveAndFlush(client);

        final CreditOffer creditOffer = creditOfferMapper.fromCreateDto(creditOfferCreateDto);
        creditOfferRepository.save(creditOffer);

        paymentScheduleRepository.save(createPaymentSchedule(creditOffer));

        return creditOfferMapper.toGetDto(creditOffer);
    }

    @Override
    @Transactional
    public CreditOfferGetDto updateCreditOffer(CreditOfferUpdateDto creditOfferUpdateDto) {
        final CreditOffer creditOffer = creditOfferRepository.findById(creditOfferUpdateDto.getId()).orElseThrow(NullPointerException::new);
        paymentScheduleRepository.deleteByCreditOffer(creditOffer);
        creditOffer.setCreditAmount(creditOfferUpdateDto.getCreditAmount());
        creditOfferRepository.saveAndFlush(creditOffer);
        paymentScheduleRepository.save(createPaymentSchedule(creditOffer));

        return creditOfferMapper.toGetDto(creditOffer);
    }

    @Override
    public CreditOfferGetDto getCreditOffer(UUID id) {
        final CreditOffer creditOffer = creditOfferRepository.findById(id).orElseThrow(NullPointerException::new);

        return creditOfferMapper.toGetDto(creditOffer);
    }

    @Override
    @Transactional
    public void deleteCreditOffer(UUID id) {
        final CreditOffer creditOffer = creditOfferRepository.findById(id).orElseThrow(NullPointerException::new);
        final Client client = creditOffer.getClient();
        client.getCredits().remove(creditOffer.getCredit());
        clientRepository.saveAndFlush(client);
        paymentScheduleRepository.deleteByCreditOffer(creditOffer);
        creditOfferRepository.deleteById(id);
    }

    @Override
    public List<CreditOfferGetDto> getAllCreditOffers() {
        return creditOfferMapper.toGetDto(creditOfferRepository.findAll());
    }

    @Override
    public List<CreditOfferGetDto> getAllCreditOffersWhereClientIs(UUID clientId) {
        final Client client = clientRepository.findById(clientId).orElseThrow(NullPointerException::new);

        return creditOfferMapper.toGetDto(creditOfferRepository.findAllByClientIs(client));
    }

    private PaymentSchedule createPaymentSchedule(CreditOffer creditOffer) {
        final PaymentSchedule paymentSchedule = new PaymentSchedule();
        paymentSchedule.setCreditOffer(creditOffer);
        paymentSchedule.setAmountPayment(0.0);
        paymentSchedule.setRepaymentAmountLoanBody(0.0);
        paymentSchedule.setRepaymentAmountPercentages(0.0);
        paymentSchedule.setIndebtedness(creditOffer.getCreditAmount());

        return paymentSchedule;
    }
}
