package com.haulmont.bank.data.dto.update;

import java.util.Objects;
import java.util.UUID;

public class CreditOfferUpdateDto {

    private UUID id;
    private Double creditAmount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
        CreditOfferUpdateDto that = (CreditOfferUpdateDto) o;
        return Objects.equals(id, that.id) && Objects.equals(creditAmount, that.creditAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditAmount);
    }

    @Override
    public String toString() {
        return "CreditOfferUpdateDto{" +
                "id=" + id +
                ", creditAmount=" + creditAmount +
                '}';
    }
}
