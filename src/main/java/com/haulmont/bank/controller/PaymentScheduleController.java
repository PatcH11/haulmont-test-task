package com.haulmont.bank.controller;

import com.haulmont.bank.data.dto.PaymentScheduleDto;
import com.haulmont.bank.service.IPaymentScheduleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "api/v1/paymentschedule",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PaymentScheduleController {

    private final IPaymentScheduleService paymentScheduleService;

    public PaymentScheduleController(IPaymentScheduleService paymentScheduleService) {
        this.paymentScheduleService = paymentScheduleService;
    }

    @PostMapping
    public PaymentScheduleDto createPaymentSchedule(@RequestBody PaymentScheduleDto paymentScheduleDto) {
        return paymentScheduleService.createPaymentSchedule(paymentScheduleDto);
    }

    @GetMapping("/{id}")
    public PaymentScheduleDto getPaymentSchedule(@PathVariable UUID id) {
        return paymentScheduleService.getPaymentSchedule(id);
    }

    @GetMapping
    public List<PaymentScheduleDto> getAllPaymentSchedules() {
        return paymentScheduleService.getAllPaymentSchedules();
    }
}
