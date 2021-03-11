package com.haulmont.bank.data.dto.create;

import java.util.Objects;
import java.util.UUID;

public class CreditOfferCreateDto {

    private UUID clientId;
    private UUID creditId;
    private Double creditAmount;

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getCreditId() {
        return creditId;
    }

    public void setCreditId(UUID creditId) {
        this.creditId = creditId;
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
        CreditOfferCreateDto that = (CreditOfferCreateDto) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(creditId, that.creditId) && Objects.equals(creditAmount, that.creditAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, creditId, creditAmount);
    }

    @Override
    public String toString() {
        return "CreditOfferCreateDto{" +
                "clientId=" + clientId +
                ", creditId=" + creditId +
                ", creditAmount=" + creditAmount +
                '}';
    }
}
