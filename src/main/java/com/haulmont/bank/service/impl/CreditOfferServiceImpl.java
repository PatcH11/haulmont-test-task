package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.CreditOfferDto;
import com.haulmont.bank.data.mapstruct.CreditOfferMapper;
import com.haulmont.bank.data.model.CreditOffer;
import com.haulmont.bank.data.repository.CreditOfferRepository;
import com.haulmont.bank.service.ICreditOfferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CreditOfferServiceImpl implements ICreditOfferService {

    private final CreditOfferRepository creditOfferRepository;
    private final CreditOfferMapper creditOfferMapper;

    public CreditOfferServiceImpl(CreditOfferRepository creditOfferRepository,
                                  CreditOfferMapper creditOfferMapper) {
        this.creditOfferRepository = creditOfferRepository;
        this.creditOfferMapper = creditOfferMapper;
    }

    @Override
    @Transactional
    public CreditOfferDto createCreditOffer(CreditOfferDto creditOfferDto) {
        final CreditOffer credit = creditOfferMapper.toEntity(creditOfferDto);
        creditOfferRepository.save(credit);

        return creditOfferMapper.toDto(credit);
    }

    @Override
    public CreditOfferDto updateCreditOffer(CreditOfferDto creditOfferDto) {
        return null;
    }

    @Override
    public CreditOfferDto getCreditOffer(UUID id) {
        final CreditOffer creditOffer = creditOfferRepository.findById(id).orElseThrow(NullPointerException::new);

        return creditOfferMapper.toDto(creditOffer);
    }

    @Override
    public List<CreditOfferDto> getAllCreditOffers() {
        return creditOfferMapper.toDto(creditOfferRepository.findAll());
    }
}
