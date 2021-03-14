package com.haulmont.bank.controller;

import com.haulmont.bank.data.dto.create.CreditOfferCreateDto;
import com.haulmont.bank.data.dto.get.CreditOfferGetDto;
import com.haulmont.bank.data.dto.update.CreditOfferUpdateDto;
import com.haulmont.bank.service.ICreditOfferService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "/api/credit-offer",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CreditOfferController {

    private final ICreditOfferService creditOfferService;

    public CreditOfferController(ICreditOfferService creditOfferService) {
        this.creditOfferService = creditOfferService;
    }

    @PostMapping
    public CreditOfferGetDto createCreditOffer(@RequestBody CreditOfferCreateDto creditOfferCreateDto) {
        return creditOfferService.createCreditOffer(creditOfferCreateDto);
    }

    @PutMapping
    public CreditOfferGetDto updateCreditOffer(@RequestBody CreditOfferUpdateDto creditOfferUpdateDto) {
        return creditOfferService.updateCreditOffer(creditOfferUpdateDto);
    }

    @GetMapping("/{id}")
    public CreditOfferGetDto getCreditOffer(@PathVariable UUID id) {
        return creditOfferService.getCreditOffer(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCreditOffer(@PathVariable UUID id) {
        creditOfferService.deleteCreditOffer(id);
    }

    @GetMapping
    public List<CreditOfferGetDto> getAllCreditOffers() {
        return creditOfferService.getAllCreditOffers();
    }

    @GetMapping("/all/{clientId}")
    public List<CreditOfferGetDto> getAllCreditOffersWhereClientIs(@PathVariable UUID clientId) {
        return creditOfferService.getAllCreditOffersWhereClientIs(clientId);
    }
}
