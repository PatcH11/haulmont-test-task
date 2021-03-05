package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.ClientDto;
import com.haulmont.bank.data.mapstruct.ClientMapper;
import com.haulmont.bank.data.model.Client;
import com.haulmont.bank.data.repository.ClientRepository;
import com.haulmont.bank.service.IClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository,
                             ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    @Transactional
    public ClientDto createClient(ClientDto clientDto) {
        final Client client = clientMapper.toEntity(clientDto);
        clientRepository.save(client);

        return clientMapper.toDto(client);
    }

    @Override
    @Transactional
    public ClientDto updateClient(ClientDto clientDto) {
        final Client client = clientRepository.findById(clientDto.getId()).orElseThrow(NullPointerException::new);
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setPatronymic(clientDto.getPatronymic());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setEmail(clientDto.getEmail());
        client.setPassportNumber(clientDto.getPassportNumber());

        final Client updatedClient = clientRepository.saveAndFlush(client);

        return clientMapper.toDto(updatedClient);
    }

    @Override
    public ClientDto getClient(UUID id) {
        final Client client = clientRepository.findById(id).orElse(null);

        return clientMapper.toDto(client);
    }

    @Override
    @Transactional
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientMapper.toDto(clientRepository.findAll());
    }
}
