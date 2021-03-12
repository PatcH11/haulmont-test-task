package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.create.PaymentScheduleCreateDto;
import com.haulmont.bank.data.dto.get.PaymentScheduleGetAndUpdateDto;

import java.util.List;
import java.util.UUID;

public interface IPaymentScheduleService {

    PaymentScheduleGetAndUpdateDto createPaymentSchedule(PaymentScheduleCreateDto paymentScheduleCreateDto) throws IllegalAccessException;

    PaymentScheduleGetAndUpdateDto updatePaymentSchedule(PaymentScheduleGetAndUpdateDto paymentScheduleGetAndUpdateDto);

    PaymentScheduleGetAndUpdateDto getPaymentSchedule(UUID id);

    void deletePaymentSchedule(UUID id);

    List<PaymentScheduleGetAndUpdateDto> getAllPaymentSchedules();

    List<PaymentScheduleGetAndUpdateDto> getAllPaymentSchedulesWhereClientAndCreditIs(UUID clientId, UUID creditId);
}
