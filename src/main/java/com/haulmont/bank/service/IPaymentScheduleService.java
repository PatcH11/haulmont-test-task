package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.create.PaymentScheduleCreateDto;
import com.haulmont.bank.data.dto.get.PaymentScheduleGetDto;
import com.haulmont.bank.exception.PaymentScheduleException;

import java.util.List;
import java.util.UUID;

public interface IPaymentScheduleService {

    PaymentScheduleGetDto createPaymentSchedule(PaymentScheduleCreateDto paymentScheduleCreateDto) throws PaymentScheduleException;

    PaymentScheduleGetDto getPaymentSchedule(UUID id);

    void deletePaymentSchedule(UUID id);

    List<PaymentScheduleGetDto> getAllPaymentSchedules();

    List<PaymentScheduleGetDto> getAllPaymentSchedulesWhereClientAndCreditIs(UUID clientId, UUID creditId);
}
