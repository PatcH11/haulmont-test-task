package com.haulmont.bank.service;

import com.haulmont.bank.data.dto.create.ClientCreateDto;
import com.haulmont.bank.data.dto.get.ClientGetAndUpdateDto;

import java.util.List;
import java.util.UUID;

public interface IClientService {

    ClientGetAndUpdateDto createClient(ClientCreateDto clientCreateDto);

    ClientGetAndUpdateDto updateClient(ClientGetAndUpdateDto clientGetAndUpdateDto);

    ClientGetAndUpdateDto getClient(UUID id);

    void deleteClient(UUID id);

    List<ClientGetAndUpdateDto> getAllClients();
}
