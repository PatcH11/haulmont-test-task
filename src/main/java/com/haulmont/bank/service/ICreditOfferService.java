package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.create.CreditOfferCreateDto;
import com.haulmont.bank.data.dto.get.CreditOfferGetDto;
import com.haulmont.bank.data.dto.update.CreditOfferUpdateDto;

import java.util.List;
import java.util.UUID;

public interface ICreditOfferService {

    CreditOfferGetDto createCreditOffer(CreditOfferCreateDto creditOfferCreateDto);

    CreditOfferGetDto updateCreditOffer(CreditOfferUpdateDto creditOfferUpdateDto);

    CreditOfferGetDto getCreditOffer(UUID id);

    void deleteCreditOffer(UUID id);

    List<CreditOfferGetDto> getAllCreditOffers();

    List<CreditOfferGetDto> getAllCreditOffersWhereClientIs(UUID clientId);
}
