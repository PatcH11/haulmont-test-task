package com.haulmont.bank.controller;

import com.haulmont.bank.data.dto.create.PaymentScheduleCreateDto;
import com.haulmont.bank.data.dto.get.PaymentScheduleGetDto;
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
        path = "/api/payment-schedule",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PaymentScheduleController {

    private final IPaymentScheduleService paymentScheduleService;

    public PaymentScheduleController(IPaymentScheduleService paymentScheduleService) {
        this.paymentScheduleService = paymentScheduleService;
    }

    @PostMapping
    public PaymentScheduleGetDto createPaymentSchedule(@RequestBody PaymentScheduleCreateDto paymentScheduleCreateDto) {
        return paymentScheduleService.createPaymentSchedule(paymentScheduleCreateDto);
    }

    @GetMapping("/{id}")
    public PaymentScheduleGetDto getPaymentSchedule(@PathVariable UUID id) {
        return paymentScheduleService.getPaymentSchedule(id);
    }

    @GetMapping
    public List<PaymentScheduleGetDto> getAllPaymentSchedules() {
        return paymentScheduleService.getAllPaymentSchedules();
    }

    @GetMapping("all/{clientId}/{creditId}")
    public List<PaymentScheduleGetDto> getAllPaymentSchedulesWhereClientAndCreditIs(@PathVariable UUID clientId,
                                                                                    @PathVariable UUID creditId) {
        return paymentScheduleService.getAllPaymentSchedulesWhereClientAndCreditIs(clientId, creditId);
    }
}
