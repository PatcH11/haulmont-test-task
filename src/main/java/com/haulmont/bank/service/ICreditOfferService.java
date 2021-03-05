package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.CreditOfferDto;

import java.util.List;
import java.util.UUID;

public interface ICreditOfferService {

    CreditOfferDto createCreditOffer(CreditOfferDto creditOfferDto);

    CreditOfferDto updateCreditOffer(CreditOfferDto creditOfferDto);

    CreditOfferDto getCreditOffer(UUID id);

    List<CreditOfferDto> getAllCreditOffers();
}
