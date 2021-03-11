package com.haulmont.bank.data.dto.get;

import java.util.Objects;
import java.util.UUID;

public class CreditOfferGetAndUpdateDto {

    private UUID id;
    private ClientGetAndUpdateDto client;
    private CreditGetAndUpdateDto credit;
    private Double creditAmount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ClientGetAndUpdateDto getClient() {
        return client;
    }

    public void setClient(ClientGetAndUpdateDto client) {
        this.client = client;
    }

    public CreditGetAndUpdateDto getCredit() {
        return credit;
    }

    public void setCredit(CreditGetAndUpdateDto credit) {
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
        CreditOfferGetAndUpdateDto that = (CreditOfferGetAndUpdateDto) o;
        return Objects.equals(id, that.id) && Objects.equals(client, that.client) && Objects.equals(credit, that.credit) && Objects.equals(creditAmount, that.creditAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, credit, creditAmount);
    }

    @Override
    public String toString() {
        return "CreditOfferGetAndUpdateDto{" +
                "id=" + id +
                ", client=" + client +
                ", credit=" + credit +
                ", creditAmount=" + creditAmount +
                '}';
    }
}
