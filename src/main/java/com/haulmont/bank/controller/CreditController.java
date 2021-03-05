package com.haulmont.bank.controller;

import com.haulmont.bank.data.dto.CreditDto;
import com.haulmont.bank.service.ICreditService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "api/v1/credit",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CreditController {

    private final ICreditService creditService;

    public CreditController(ICreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    public CreditDto createCredit(@RequestBody CreditDto creditDto) {
        return creditService.createCredit(creditDto);
    }

    @GetMapping("/{id}")
    public CreditDto getCredit(@PathVariable UUID id) {
        return creditService.getCredit(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCredit(@PathVariable UUID id) {
        creditService.deleteCredit(id);
    }

    @GetMapping
    public List<CreditDto> getAllCredits() {
        return creditService.getAllCredits();
    }
}
