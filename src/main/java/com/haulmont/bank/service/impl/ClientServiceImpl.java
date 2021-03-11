package com.haulmont.bank.service.impl;

import com.haulmont.bank.data.dto.create.ClientCreateDto;
import com.haulmont.bank.data.dto.get.ClientGetAndUpdateDto;
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
    public ClientGetAndUpdateDto createClient(ClientCreateDto clientCreateDto) {
        final Client client = clientMapper.fromCreateDto(clientCreateDto);
        clientRepository.save(client);

        return clientMapper.toGetAndUpdateDto(client);
    }

    @Override
    @Transactional
    public ClientGetAndUpdateDto updateClient(ClientGetAndUpdateDto clientGetAndUpdateDto) {
        final Client client = clientRepository.findById(clientGetAndUpdateDto.getId()).orElseThrow(NullPointerException::new);
        client.setFirstName(clientGetAndUpdateDto.getFirstName());
        client.setLastName(clientGetAndUpdateDto.getLastName());
        client.setPatronymic(clientGetAndUpdateDto.getPatronymic());
        client.setPhoneNumber(clientGetAndUpdateDto.getPhoneNumber());
        client.setEmail(clientGetAndUpdateDto.getEmail());
        client.setPassportNumber(clientGetAndUpdateDto.getPassportNumber());

        final Client updatedClient = clientRepository.saveAndFlush(client);

        return clientMapper.toGetAndUpdateDto(updatedClient);
    }

    @Override
    public ClientGetAndUpdateDto getClient(UUID id) {
        final Client client = clientRepository.findById(id).orElseThrow(NullPointerException::new);

        return clientMapper.toGetAndUpdateDto(client);
    }

    @Override
    @Transactional
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientGetAndUpdateDto> getAllClients() {
        return clientMapper.toGetDto(clientRepository.findAll());
    }
}
