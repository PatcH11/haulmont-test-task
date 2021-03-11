package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.create.CreditCreateDto;
import com.haulmont.bank.data.dto.get.CreditGetAndUpdateDto;
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
    public CreditGetAndUpdateDto createCredit(CreditCreateDto creditCreateDto) {
        final Credit credit = creditMapper.fromCreateDto(creditCreateDto);
        creditRepository.save(credit);

        return creditMapper.toGetAndUpdateDto(credit);
    }

    @Override
    @Transactional
    public CreditGetAndUpdateDto updateCredit(CreditGetAndUpdateDto creditGetAndUpdateDto) {
        final Credit credit = creditRepository.findById(creditGetAndUpdateDto.getId()).orElseThrow(NullPointerException::new);
        credit.setName(creditGetAndUpdateDto.getName());
        credit.setLoanLimit(creditGetAndUpdateDto.getLoanLimit());
        credit.setInterestRate(creditGetAndUpdateDto.getInterestRate());

        final Credit updatedCredit = creditRepository.saveAndFlush(credit);

        return creditMapper.toGetAndUpdateDto(updatedCredit);
    }

    @Override
    public CreditGetAndUpdateDto getCredit(UUID id) {
        final Credit credit = creditRepository.findById(id).orElseThrow(NullPointerException::new);

        return creditMapper.toGetAndUpdateDto(credit);
    }

    @Override
    @Transactional
    public void deleteCredit(UUID id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditGetAndUpdateDto> getAllCredits() {
        return creditMapper.toGetDto(creditRepository.findAll());
    }
}
