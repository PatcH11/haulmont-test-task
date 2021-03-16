package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.create.ClientCreateDto;
import com.haulmont.bank.data.dto.get.ClientGetDto;
import com.haulmont.bank.data.dto.update.ClientUpdateDto;
import com.haulmont.bank.data.mapstruct.ClientMapper;
import com.haulmont.bank.data.model.Client;
import com.haulmont.bank.data.model.Credit;
import com.haulmont.bank.data.repository.ClientRepository;
import com.haulmont.bank.data.repository.CreditRepository;
import com.haulmont.bank.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository,
                             CreditRepository creditRepository,
                             ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    @Transactional
    public ClientGetDto createClient(ClientCreateDto clientCreateDto) {
        final Client client = clientMapper.fromCreateDto(clientCreateDto);
        clientRepository.save(client);

        return clientMapper.toGetDto(client);
    }

    @Override
    @Transactional
    public ClientGetDto updateClient(ClientUpdateDto clientUpdateDto) {
        final Client client = clientRepository.findById(clientUpdateDto.getId()).orElseThrow(NullPointerException::new);
        client.setFirstName(clientUpdateDto.getFirstName());
        client.setLastName(clientUpdateDto.getLastName());
        client.setPatronymic(clientUpdateDto.getPatronymic());
        client.setPhoneNumber(clientUpdateDto.getPhoneNumber());
        client.setEmail(clientUpdateDto.getEmail());
        client.setPassportNumber(clientUpdateDto.getPassportNumber());

        final Client updatedClient = clientRepository.saveAndFlush(client);

        return clientMapper.toGetDto(updatedClient);
    }

    @Override
    public ClientGetDto getClient(UUID id) {
        final Client client = clientRepository.findById(id).orElseThrow(NullPointerException::new);

        return clientMapper.toGetDto(client);
    }

    @Override
    @Transactional
    public void deleteClient(UUID id) {
        final Client client = clientRepository.findById(id).orElseThrow(NullPointerException::new);
        final List<Credit> credits = creditRepository.findCreditByClientsIs(client);
        credits.forEach(credit -> client.getCredits().remove(credit));
        clientRepository.saveAndFlush(client);

        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientGetDto> getAllClients() {
        return clientMapper.toGetDto(clientRepository.findAll());
    }
}
