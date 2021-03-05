package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.CreditDto;
import com.haulmont.bank.data.mapstruct.CreditMapper;
import com.haulmont.bank.data.model.Credit;
import com.haulmont.bank.data.repository.CreditRepository;
import com.haulmont.bank.service.ICreditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CreditServiceImpl implements ICreditService {

    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;

    public CreditServiceImpl(CreditRepository creditRepository,
                             CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.creditMapper = creditMapper;
    }

    @Override
    @Transactional
    public CreditDto createCredit(CreditDto creditDto) {
        final Credit credit = creditMapper.toEntity(creditDto);
        creditRepository.save(credit);

        return creditMapper.toDto(credit);
    }

    @Override
    @Transactional
    public CreditDto updateCredit(CreditDto creditDto) {
        final Credit credit = creditRepository.findById(creditDto.getId()).orElseThrow(NullPointerException::new);
        credit.setLoanLimit(creditDto.getLoanLimit());
        credit.setInterestRate(creditDto.getInterestRate());

        final Credit updatedCredit = creditRepository.saveAndFlush(credit);

        return creditMapper.toDto(updatedCredit);
    }

    @Override
    public CreditDto getCredit(UUID id) {
        final Credit credit = creditRepository.findById(id).orElseThrow(NullPointerException::new);

        return creditMapper.toDto(credit);
    }

    @Override
    @Transactional
    public void deleteCredit(UUID id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditDto> getAllCredits() {
        return creditMapper.toDto(creditRepository.findAll());
    }
}
