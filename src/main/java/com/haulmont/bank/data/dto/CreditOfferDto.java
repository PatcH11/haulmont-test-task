package com.haulmont.bank.data.dto;

import java.util.Objects;
import java.util.UUID;

public class CreditOfferDto {

    private UUID id;
    private ClientDto client;
    private CreditDto credit;
    private Double creditAmount;

    public CreditOfferDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public CreditDto getCredit() {
        return credit;
    }

    public void setCredit(CreditDto credit) {
        this.credit = credit;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditOfferDto that = (CreditOfferDto) o;
        return Objects.equals(id, that.id) && Objects.equals(client, that.client) && Objects.equals(credit, that.credit) && Objects.equals(creditAmount, that.creditAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, credit, creditAmount);
    }

    @Override
    public String toString() {
        return "CreditOfferDto{" +
                "id=" + id +
                ", client=" + client +
                ", credit=" + credit +
                ", creditAmount=" + creditAmount +
                '}';
    }
}
