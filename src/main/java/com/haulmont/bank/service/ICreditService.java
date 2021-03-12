package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.create.CreditCreateDto;
import com.haulmont.bank.data.dto.get.CreditGetAndUpdateDto;
import com.haulmont.bank.data.model.Credit;

import java.util.List;
import java.util.UUID;

public interface ICreditService {

    CreditGetAndUpdateDto createCredit(CreditCreateDto creditCreateDto);

    CreditGetAndUpdateDto updateCredit(CreditGetAndUpdateDto creditDto);

    CreditGetAndUpdateDto getCredit(UUID id);

    void deleteCredit(UUID id);

    List<CreditGetAndUpdateDto> getAllCredits();

    List<CreditGetAndUpdateDto> getAllCreditsWhereClientIs(UUID clientId);
}
