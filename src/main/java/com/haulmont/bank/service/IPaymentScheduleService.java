package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.PaymentScheduleDto;

import java.util.List;
import java.util.UUID;

public interface IPaymentScheduleService {

    PaymentScheduleDto createPaymentSchedule(PaymentScheduleDto paymentScheduleDto);

    PaymentScheduleDto updatePaymentSchedule(PaymentScheduleDto paymentScheduleDto);

    PaymentScheduleDto getPaymentSchedule(UUID id);

    List<PaymentScheduleDto> getAllPaymentSchedules();
}
