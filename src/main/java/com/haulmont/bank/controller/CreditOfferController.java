package com.haulmont.bank.controller;

import com.haulmont.bank.data.dto.create.CreditOfferCreateDto;
import com.haulmont.bank.data.dto.get.ClientGetAndUpdateDto;
import com.haulmont.bank.data.dto.get.CreditOfferGetAndUpdateDto;
import com.haulmont.bank.service.ICreditOfferService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin
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
    public CreditOfferGetAndUpdateDto createCreditOffer(@RequestBody CreditOfferCreateDto creditOfferCreateDto) {
        return creditOfferService.createCreditOffer(creditOfferCreateDto);
    }

    @GetMapping("/{id}")
    public CreditOfferGetAndUpdateDto getCreditOffer(@PathVariable UUID id) {
        return creditOfferService.getCreditOffer(id);
    }

    @GetMapping
    public List<CreditOfferGetAndUpdateDto> getAllCreditOffers() {
        return creditOfferService.getAllCreditOffers();
    }

    @GetMapping("/all/{clientId}")
    public List<CreditOfferGetAndUpdateDto> getAllCreditOffersWhereClientIs(@PathVariable UUID clientId) {
        return creditOfferService.getAllCreditOffersWhereClientIs(clientId);
    }
}
