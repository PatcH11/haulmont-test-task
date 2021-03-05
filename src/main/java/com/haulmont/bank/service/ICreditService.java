package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.CreditDto;

import java.util.List;
import java.util.UUID;

public interface ICreditService {

    CreditDto createCredit(CreditDto creditDto);

    CreditDto updateCredit(CreditDto creditDto);

    CreditDto getCredit(UUID id);

    void deleteCredit(UUID id);

    List<CreditDto> getAllCredits();
}
