package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.create.CreditOfferCreateDto;
import com.haulmont.bank.data.dto.get.CreditOfferGetAndUpdateDto;

import java.util.List;
import java.util.UUID;

public interface ICreditOfferService {

    CreditOfferGetAndUpdateDto createCreditOffer(CreditOfferCreateDto creditOfferCreateDto);

    CreditOfferGetAndUpdateDto updateCreditOffer(CreditOfferGetAndUpdateDto creditOfferGetAndUpdateDto);

    CreditOfferGetAndUpdateDto getCreditOffer(UUID id);

    void deleteCreditOffer(UUID id);

    List<CreditOfferGetAndUpdateDto> getAllCreditOffers();
}
