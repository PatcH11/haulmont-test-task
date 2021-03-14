package com.haulmont.bank.controller;

import com.haulmont.bank.data.dto.create.CreditCreateDto;
import com.haulmont.bank.data.dto.get.CreditGetDto;
import com.haulmont.bank.data.dto.update.CreditUpdateDto;
import com.haulmont.bank.service.ICreditService;
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
        path = "/api/credit",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CreditController {

    private final ICreditService creditService;

    public CreditController(ICreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    public CreditGetDto createCredit(@RequestBody CreditCreateDto creditDto) {
        return creditService.createCredit(creditDto);
    }

    @PutMapping
    public CreditGetDto updateCredit(@RequestBody CreditUpdateDto creditUpdateDto) {
        return creditService.updateCredit(creditUpdateDto);
    }

    @GetMapping("/{id}")
    public CreditGetDto getCredit(@PathVariable UUID id) {
        return creditService.getCredit(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCredit(@PathVariable UUID id) {
        creditService.deleteCredit(id);
    }

    @GetMapping
    public List<CreditGetDto> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/all/{clientId}")
    public List<CreditGetDto> getAllCreditWhereClientIs(@PathVariable UUID clientId) {
        return creditService.getAllCreditsWhereClientIs(clientId);
    }
}
