package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.create.PaymentScheduleCreateDto;
import com.haulmont.bank.data.dto.get.PaymentScheduleGetDto;
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
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public PaymentScheduleServiceImpl(PaymentScheduleRepository paymentScheduleRepository,
                                      ClientRepository clientRepository,
                                      CreditRepository creditRepository,
                                      CreditOfferRepository creditOfferRepository,
                                      PaymentScheduleMapper paymentScheduleMapper) {
        this.paymentScheduleRepository = paymentScheduleRepository;
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
        this.creditOfferRepository = creditOfferRepository;
        this.paymentScheduleMapper = paymentScheduleMapper;
    }

    @Override
    @Transactional
    public PaymentScheduleGetDto createPaymentSchedule(PaymentScheduleCreateDto paymentScheduleCreateDto) {
        final CreditOffer creditOffer = creditOfferRepository.findById(paymentScheduleCreateDto.getCreditOfferId()).orElseThrow(NullPointerException::new);
        final PaymentSchedule lastPaymentSchedule = paymentScheduleRepository.findFirstByCreditOfferOrderByDateDesc(creditOffer);

        final PaymentSchedule paymentSchedule = paymentScheduleMapper.fromCreateDto(paymentScheduleCreateDto);

        if ((lastPaymentSchedule.getIndebtedness() - calculationMonthlyPayment(creditOffer)) < 0) {
            paymentSchedule.setAmountPayment(lastPaymentSchedule.getIndebtedness() + calculationRepaymentAmountPercentages(lastPaymentSchedule));
            paymentSchedule.setRepaymentAmountLoanBody(lastPaymentSchedule.getIndebtedness());
            paymentSchedule.setRepaymentAmountPercentages(calculationRepaymentAmountPercentages(lastPaymentSchedule));
            paymentSchedule.setIndebtedness(0.0);
        } else {
            paymentSchedule.setAmountPayment(calculationMonthlyPayment(creditOffer));
            paymentSchedule.setRepaymentAmountPercentages(calculationRepaymentAmountPercentages(lastPaymentSchedule));
            paymentSchedule.setIndebtedness(calculationIndebtedness(creditOffer, lastPaymentSchedule));
            paymentSchedule.setRepaymentAmountLoanBody(calculationRepaymentAmountLoanBody(creditOffer, lastPaymentSchedule));
        }
        paymentScheduleRepository.save(paymentSchedule);

        return paymentScheduleMapper.toGetDto(paymentSchedule);
    }

    @Override
    public PaymentScheduleGetDto getPaymentSchedule(UUID id) {
        final PaymentSchedule paymentSchedule = paymentScheduleRepository.findById(id).orElseThrow(NullPointerException::new);

        return paymentScheduleMapper.toGetDto(paymentSchedule);
    }

    @Override
    public List<PaymentScheduleGetDto> getAllPaymentSchedules() {
        return paymentScheduleMapper.toGetDto(paymentScheduleRepository.findAll());
    }

    @Override
    public List<PaymentScheduleGetDto> getAllPaymentSchedulesWhereClientAndCreditIs(UUID clientId, UUID creditId) {
        final Client client = clientRepository.findById(clientId).orElseThrow(NullPointerException::new);
        final Credit credit = creditRepository.findById(creditId).orElseThrow(NullPointerException::new);
        final CreditOffer creditOffer = creditOfferRepository.findByClientIsAndCreditIs(client, credit);

        return paymentScheduleMapper.toGetDto(paymentScheduleRepository.findByCreditOfferOrderByDate(creditOffer));
    }

    private double calculationMonthlyPayment(CreditOffer creditOffer) {
        double creditAmount = creditOffer.getCreditAmount();
        double interestRate = creditOffer.getCredit().getInterestRate() / 100 / 12;
        int numberMonths = 12;

        return creditAmount * ((interestRate * Math.pow(1 + interestRate, numberMonths)) / (Math.pow(1 + interestRate, numberMonths) - 1));
    }

    private Double calculationRepaymentAmountLoanBody(CreditOffer creditOffer, PaymentSchedule lastPaymentSchedule) {
        return calculationMonthlyPayment(creditOffer) - calculationRepaymentAmountPercentages(lastPaymentSchedule);
    }

    private Double calculationRepaymentAmountPercentages(PaymentSchedule lastPaymentSchedule) {
        Double indebtedness = lastPaymentSchedule.getIndebtedness();
        double debtPerMonth = indebtedness / 12;

        return (debtPerMonth * lastPaymentSchedule.getCreditOffer().getCredit().getInterestRate()) / 100;
    }

    private Double calculationIndebtedness(CreditOffer creditOffer, PaymentSchedule lastPaymentSchedule) {
        return (lastPaymentSchedule.getIndebtedness() - calculationMonthlyPayment(creditOffer)) + calculationRepaymentAmountPercentages(lastPaymentSchedule);
    }
}
