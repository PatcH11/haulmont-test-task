package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.create.PaymentScheduleCreateDto;
import com.haulmont.bank.data.dto.get.CreditOfferGetAndUpdateDto;
import com.haulmont.bank.data.dto.get.PaymentScheduleGetAndUpdateDto;
import com.haulmont.bank.data.mapstruct.CreditOfferMapper;
import com.haulmont.bank.data.mapstruct.PaymentScheduleMapper;
import com.haulmont.bank.data.model.Client;
import com.haulmont.bank.data.model.Credit;
import com.haulmont.bank.data.model.CreditOffer;
import com.haulmont.bank.data.model.PaymentSchedule;
import com.haulmont.bank.data.repository.ClientRepository;
import com.haulmont.bank.data.repository.CreditOfferRepository;
import com.haulmont.bank.data.repository.CreditRepository;
import com.haulmont.bank.data.repository.PaymentScheduleRepository;
import com.haulmont.bank.service.IPaymentScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentScheduleServiceImpl implements IPaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;
    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final CreditOfferRepository creditOfferRepository;
    private final PaymentScheduleMapper paymentScheduleMapper;
    private final CreditOfferMapper creditOfferMapper;

    public PaymentScheduleServiceImpl(PaymentScheduleRepository paymentScheduleRepository,
                                      ClientRepository clientRepository,
                                      CreditRepository creditRepository,
                                      CreditOfferRepository creditOfferRepository,
                                      PaymentScheduleMapper paymentScheduleMapper,
                                      CreditOfferMapper creditOfferMapper) {
        this.paymentScheduleRepository = paymentScheduleRepository;
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
        this.creditOfferRepository = creditOfferRepository;
        this.paymentScheduleMapper = paymentScheduleMapper;
        this.creditOfferMapper = creditOfferMapper;
    }

    @Override
    @Transactional
    public PaymentScheduleGetAndUpdateDto createPaymentSchedule(PaymentScheduleCreateDto paymentScheduleCreateDto) throws IllegalAccessException {
        final CreditOffer creditOffer = creditOfferRepository.findById(paymentScheduleCreateDto.getCreditOfferId()).orElseThrow(NullPointerException::new);
        final PaymentSchedule lastPS = paymentScheduleRepository.findFirstByCreditOfferOrderByDateDesc(creditOffer);

        final PaymentSchedule paymentSchedule = paymentScheduleMapper.fromCreateDto(paymentScheduleCreateDto);
        //TODO подставлять в поле amountPayment значенеие из метода, который расчитывает ежемесячный платёж.
        if (lastPS.getIndebtedness() == 0.0) {
            throw new IllegalAccessException();
        } else if ((lastPS.getIndebtedness() - calculationMonthlyPayment(creditOfferMapper.toGetAndUpdateDto(creditOffer))) < 0) {
            paymentSchedule.setAmountPayment(calculationMonthlyPayment(creditOfferMapper.toGetAndUpdateDto(creditOffer)));
            paymentSchedule.setRepaymentAmountLoanBody(0.0);
            paymentSchedule.setRepaymentAmountPercentages(0.0);
            paymentSchedule.setIndebtedness(0.0);
        } else {
            paymentSchedule.setAmountPayment(calculationMonthlyPayment(creditOfferMapper.toGetAndUpdateDto(creditOffer)));
            paymentSchedule.setRepaymentAmountLoanBody(calculationMonthlyPayment(creditOfferMapper.toGetAndUpdateDto(creditOffer)) - calculationRepaymentAmountPercentages(paymentScheduleMapper.toGetAndUpdateDto(lastPS)));
            paymentSchedule.setRepaymentAmountPercentages(calculationRepaymentAmountPercentages(paymentScheduleMapper.toGetAndUpdateDto(lastPS)));
            paymentSchedule.setIndebtedness(lastPS.getIndebtedness() - calculationMonthlyPayment(creditOfferMapper.toGetAndUpdateDto(creditOffer)));
        }
        paymentScheduleRepository.save(paymentSchedule);

        return paymentScheduleMapper.toGetAndUpdateDto(paymentSchedule);
    }

    @Override
    @Transactional
    public PaymentScheduleGetAndUpdateDto updatePaymentSchedule(PaymentScheduleGetAndUpdateDto paymentScheduleGetAndUpdateDto) {
        return null;
    }

    @Override
    public PaymentScheduleGetAndUpdateDto getPaymentSchedule(UUID id) {
        final PaymentSchedule paymentSchedule = paymentScheduleRepository.findById(id).orElseThrow(NullPointerException::new);

        return paymentScheduleMapper.toGetAndUpdateDto(paymentSchedule);
    }

    @Override
    @Transactional
    public void deletePaymentSchedule(UUID id) {

    }

    @Override
    public List<PaymentScheduleGetAndUpdateDto> getAllPaymentSchedules() {
        return paymentScheduleMapper.toGetDto(paymentScheduleRepository.findAll());
    }

    @Override
    public List<PaymentScheduleGetAndUpdateDto> getAllPaymentSchedulesWhereClientAndCreditIs(UUID clientId, UUID creditId) {
        final Client client = clientRepository.findById(clientId).orElseThrow(NullPointerException::new);
        final Credit credit = creditRepository.findById(creditId).orElseThrow(NullPointerException::new);
        final CreditOffer creditOffer = creditOfferRepository.findByClientIsAndCreditIs(client, credit);

        return paymentScheduleMapper.toGetDto(paymentScheduleRepository.findByCreditOfferOrderByDate(creditOffer));
    }

    private double calculationMonthlyPayment(CreditOfferGetAndUpdateDto creditOfferGetAndUpdateDto) {
        double creditAmount = creditOfferGetAndUpdateDto.getCreditAmount();
        double interestRate = creditOfferGetAndUpdateDto.getCredit().getInterestRate() / 100 / 12;
        int numberMonths = 12;

        return creditAmount * ((interestRate * Math.pow(1 + interestRate, numberMonths)) / (Math.pow(1 + interestRate, numberMonths) - 1));
    }

    private Double calculationRepaymentAmountLoanBody(PaymentScheduleGetAndUpdateDto paymentScheduleGetAndUpdateDto) {
        Double creditAmount = paymentScheduleGetAndUpdateDto.getCreditOffer().getCreditAmount();

        return creditAmount - calculationRepaymentAmountPercentages(paymentScheduleGetAndUpdateDto);
    }

    private Double calculationRepaymentAmountPercentages(PaymentScheduleGetAndUpdateDto paymentScheduleGetAndUpdateDto) {
        Double indebtedness = paymentScheduleGetAndUpdateDto.getIndebtedness();
        double aa = indebtedness / 12;

        return aa / 10;
    }
}
