package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.create.CreditOfferCreateDto;
import com.haulmont.bank.data.dto.get.CreditOfferGetAndUpdateDto;
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
                                  CreditRepository creditRepository, PaymentScheduleRepository paymentScheduleRepository,
                                  CreditOfferMapper creditOfferMapper) {
        this.creditOfferRepository = creditOfferRepository;
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
        this.paymentScheduleRepository = paymentScheduleRepository;
        this.creditOfferMapper = creditOfferMapper;
    }

    @Override
    @Transactional
    public CreditOfferGetAndUpdateDto createCreditOffer(CreditOfferCreateDto creditOfferCreateDto) {
        final Client client = clientRepository.findById(creditOfferCreateDto.getClientId()).orElseThrow(NullPointerException::new);
        final Credit credit = creditRepository.findById(creditOfferCreateDto.getCreditId()).orElseThrow(NullPointerException::new);
        client.getCredits().add(credit);
        clientRepository.saveAndFlush(client);

        final CreditOffer creditOffer = creditOfferMapper.fromCreateDto(creditOfferCreateDto);
        creditOfferRepository.save(creditOffer);

        final PaymentSchedule paymentSchedule = new PaymentSchedule();
        paymentSchedule.setCreditOffer(creditOffer);
        paymentSchedule.setAmountPayment(0.0);
        paymentSchedule.setRepaymentAmountLoanBody(0.0);
        paymentSchedule.setRepaymentAmountPercentages(0.0);
        paymentSchedule.setIndebtedness(creditOffer.getCreditAmount());
        paymentScheduleRepository.save(paymentSchedule);

        return creditOfferMapper.toGetAndUpdateDto(creditOffer);
    }

    @Override
    public CreditOfferGetAndUpdateDto updateCreditOffer(CreditOfferGetAndUpdateDto creditOfferGetAndUpdateDto) {
        final CreditOffer creditOffer = creditOfferRepository.findById(creditOfferGetAndUpdateDto.getId()).orElseThrow(NullPointerException::new);
        final Client client = clientRepository.findById(creditOfferGetAndUpdateDto.getClient().getId()).orElseThrow(NullPointerException::new);
        final Credit credit = creditRepository.findById(creditOfferGetAndUpdateDto.getCredit().getId()).orElseThrow(NullPointerException::new);
        creditOffer.setClient(client);
        creditOffer.setCredit(credit);
        creditOffer.setCreditAmount(creditOfferGetAndUpdateDto.getCreditAmount());
        return null;
    }

    @Override
    public CreditOfferGetAndUpdateDto getCreditOffer(UUID id) {
        final CreditOffer creditOffer = creditOfferRepository.findById(id).orElseThrow(NullPointerException::new);

        return creditOfferMapper.toGetAndUpdateDto(creditOffer);
    }

    @Override
    public void deleteCreditOffer(UUID id) {

    }

    @Override
    public List<CreditOfferGetAndUpdateDto> getAllCreditOffers() {
        return creditOfferMapper.toGetDto(creditOfferRepository.findAll());
    }
}
