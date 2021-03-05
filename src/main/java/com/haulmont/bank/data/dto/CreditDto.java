package com.haulmont.bank.data.dto;

import com.haulmont.bank.data.model.Client;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class CreditDto {

    private UUID id;
    private Long loanLimit;
    private Double interestRate;
    private Set<Client> clients;

    public CreditDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(Long loanLimit) {
        this.loanLimit = loanLimit;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditDto creditDto = (CreditDto) o;
        return Objects.equals(id, creditDto.id) && Objects.equals(loanLimit, creditDto.loanLimit) && Objects.equals(interestRate, creditDto.interestRate) && Objects.equals(clients, creditDto.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loanLimit, interestRate, clients);
    }

    @Override
    public String toString() {
        return "CreditDto{" +
                "id=" + id +
                ", loanLimit=" + loanLimit +
                ", interestRate=" + interestRate +
                ", clients=" + clients +
                '}';
    }
}
