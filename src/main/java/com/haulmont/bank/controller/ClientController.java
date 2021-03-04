package com.haulmont.bank.controller;

import com.haulmont.bank.data.dto.ClientDto;
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
        path = "api/v1/clients",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientDto createClient(@RequestBody ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @PutMapping
    public ClientDto updateClient(UUID id, @RequestBody ClientDto clientDto) {
        clientDto.setId(id);

        return clientService.updateClient(clientDto);
    }

    @GetMapping("/{id}")
    public ClientDto getClient(@PathVariable UUID id) {
        return clientService.getClient(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
    }

    @GetMapping
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }
}
