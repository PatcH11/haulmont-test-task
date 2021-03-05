package com.haulmont.bank.controller;

import com.haulmont.bank.data.dto.CreditOfferDto;
import com.haulmont.bank.service.ICreditOfferService;
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
        path = "api/v1/creditoffer",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CreditOfferController {

    private final ICreditOfferService creditOfferService;

    public CreditOfferController(ICreditOfferService creditOfferService) {
        this.creditOfferService = creditOfferService;
    }

    @PostMapping
    public CreditOfferDto createCreditOffer(@RequestBody CreditOfferDto creditOfferDto) {
        return creditOfferService.createCreditOffer(creditOfferDto);
    }

    @GetMapping("/{id}")
    public CreditOfferDto getCreditOffer(@PathVariable UUID id) {
        return creditOfferService.getCreditOffer(id);
    }

    @GetMapping
    public List<CreditOfferDto> getAllCreditOffers() {
        return creditOfferService.getAllCreditOffers();
    }
}
