package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.PaymentScheduleDto;
import com.haulmont.bank.data.mapstruct.PaymentScheduleMapper;
import com.haulmont.bank.data.model.PaymentSchedule;
import com.haulmont.bank.data.repository.PaymentScheduleRepository;
import com.haulmont.bank.service.IPaymentScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentScheduleServiceImpl implements IPaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;
    private final PaymentScheduleMapper paymentScheduleMapper;

    public PaymentScheduleServiceImpl(PaymentScheduleRepository paymentScheduleRepository,
                                      PaymentScheduleMapper paymentScheduleMapper) {
        this.paymentScheduleRepository = paymentScheduleRepository;
        this.paymentScheduleMapper = paymentScheduleMapper;
    }

    @Override
    @Transactional
    public PaymentScheduleDto createPaymentSchedule(PaymentScheduleDto paymentScheduleDto) {
        final PaymentSchedule paymentSchedule = paymentScheduleMapper.toEntity(paymentScheduleDto);
        paymentScheduleRepository.save(paymentSchedule);

        return paymentScheduleMapper.toDto(paymentSchedule);
    }

    @Override
    public PaymentScheduleDto updatePaymentSchedule(PaymentScheduleDto paymentScheduleDto) {
        return null;
    }

    @Override
    public PaymentScheduleDto getPaymentSchedule(UUID id) {
        final PaymentSchedule paymentSchedule = paymentScheduleRepository.findById(id).orElseThrow(NullPointerException::new);

        return paymentScheduleMapper.toDto(paymentSchedule);
    }

    @Override
    public List<PaymentScheduleDto> getAllPaymentSchedules() {
        return paymentScheduleMapper.toDto(paymentScheduleRepository.findAll());
    }
}
