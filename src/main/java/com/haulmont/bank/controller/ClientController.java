package com.haulmont.bank.controller;

import com.haulmont.bank.data.dto.create.ClientCreateDto;
import com.haulmont.bank.data.dto.get.ClientGetDto;
import com.haulmont.bank.data.dto.update.ClientUpdateDto;
import com.haulmont.bank.service.IClientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "/api/client",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientGetDto createClient(@RequestBody ClientCreateDto clientCreateDto) {
        return clientService.createClient(clientCreateDto);
    }

    @PutMapping
    public ClientGetDto updateClient(@RequestBody ClientUpdateDto clientUpdateDto) {
        return clientService.updateClient(clientUpdateDto);
    }

    @GetMapping("/{id}")
    public ClientGetDto getClient(@PathVariable UUID id) {
        return clientService.getClient(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
    }

    @GetMapping
    public List<ClientGetDto> getAllClients() {
        return clientService.getAllClients();
    }
}
