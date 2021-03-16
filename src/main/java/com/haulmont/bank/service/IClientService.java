package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.create.ClientCreateDto;
import com.haulmont.bank.data.dto.get.ClientGetDto;
import com.haulmont.bank.data.dto.update.ClientUpdateDto;

import java.util.List;
import java.util.UUID;

public interface IClientService {

    ClientGetDto createClient(ClientCreateDto clientCreateDto);

    ClientGetDto updateClient(ClientUpdateDto clientUpdateDto);

    ClientGetDto getClient(UUID id);

    void deleteClient(UUID id);

    List<ClientGetDto> getAllClients();

    List<ClientGetDto> getAllClientsWhereCreditsNotContains(UUID creditId);
}
