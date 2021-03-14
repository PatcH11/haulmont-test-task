package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.create.CreditCreateDto;
import com.haulmont.bank.data.dto.get.CreditGetDto;
import com.haulmont.bank.data.dto.update.CreditUpdateDto;

import java.util.List;
import java.util.UUID;

public interface ICreditService {

    CreditGetDto createCredit(CreditCreateDto creditCreateDto);

    CreditGetDto updateCredit(CreditUpdateDto creditUpdateDto);

    CreditGetDto getCredit(UUID id);

    void deleteCredit(UUID id);

    List<CreditGetDto> getAllCredits();

    List<CreditGetDto> getAllCreditsWhereClientIs(UUID clientId);
}
