package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.create.CreditCreateDto;
import com.haulmont.bank.data.dto.get.CreditGetDto;
import com.haulmont.bank.data.dto.update.CreditUpdateDto;
import com.haulmont.bank.data.mapstruct.CreditMapper;
import com.haulmont.bank.data.model.Client;
import com.haulmont.bank.data.model.Credit;
import com.haulmont.bank.data.repository.ClientRepository;
import com.haulmont.bank.data.repository.CreditRepository;
import com.haulmont.bank.service.ICreditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CreditServiceImpl implements ICreditService {

    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final CreditMapper creditMapper;

    public CreditServiceImpl(CreditRepository creditRepository,
                             ClientRepository clientRepository,
                             CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.clientRepository = clientRepository;
        this.creditMapper = creditMapper;
    }

    @Override
    @Transactional
    public CreditGetDto createCredit(CreditCreateDto creditCreateDto) {
        final Credit credit = creditMapper.fromCreateDto(creditCreateDto);
        creditRepository.save(credit);

        return creditMapper.toGetDto(credit);
    }

    @Override
    @Transactional
    public CreditGetDto updateCredit(CreditUpdateDto creditUpdateDto) {
        final Credit credit = creditRepository.findById(creditUpdateDto.getId()).orElseThrow(NullPointerException::new);
        credit.setName(creditUpdateDto.getName());

        final Credit updatedCredit = creditRepository.saveAndFlush(credit);

        return creditMapper.toGetDto(updatedCredit);
    }

    @Override
    public CreditGetDto getCredit(UUID id) {
        final Credit credit = creditRepository.findById(id).orElseThrow(NullPointerException::new);

        return creditMapper.toGetDto(credit);
    }

    @Override
    @Transactional
    public void deleteCredit(UUID id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditGetDto> getAllCredits() {
        return creditMapper.toGetDto(creditRepository.findAll());
    }

    @Override
    public List<CreditGetDto> getAllCreditsWhereClientIs(UUID clientId) {
        final Client client = clientRepository.findById(clientId).orElseThrow(NullPointerException::new);

        return creditMapper.toGetDto(creditRepository.findCreditByClientsIs(client));
    }
}
