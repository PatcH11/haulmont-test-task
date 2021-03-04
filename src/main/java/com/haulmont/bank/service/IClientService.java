package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.ClientDto;

import java.util.List;
import java.util.UUID;

public interface IClientService {

    ClientDto createClient(ClientDto clientDto);

    ClientDto updateClient(ClientDto clientDto);

    ClientDto getClient(UUID id);

    void deleteClient(UUID id);

    List<ClientDto> getAllClients();
}
