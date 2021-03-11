package com.haulmont.bank.controller;

import com.haulmont.bank.data.dto.create.CreditCreateDto;
import com.haulmont.bank.data.dto.get.CreditGetAndUpdateDto;
import com.haulmont.bank.service.ICreditService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin
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
    public CreditGetAndUpdateDto createCredit(@RequestBody CreditCreateDto creditDto) {
        return creditService.createCredit(creditDto);
    }

    @PutMapping
    public CreditGetAndUpdateDto updateCredit(@RequestBody CreditGetAndUpdateDto creditGetAndUpdateDto) {
        return creditService.updateCredit(creditGetAndUpdateDto);
    }

    @GetMapping("/{id}")
    public CreditGetAndUpdateDto getCredit(@PathVariable UUID id) {
        return creditService.getCredit(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCredit(@PathVariable UUID id) {
        creditService.deleteCredit(id);
    }

    @GetMapping
    public List<CreditGetAndUpdateDto> getAllCredits() {
        return creditService.getAllCredits();
    }
}
