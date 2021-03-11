package com.haulmont.bank.data.dto.create;

import java.util.Objects;
import java.util.UUID;

public class PaymentScheduleCreateDto {

    private UUID creditOfferId;

    public UUID getCreditOfferId() {
        return creditOfferId;
    }

    public void setCreditOfferId(UUID creditOfferId) {
        this.creditOfferId = creditOfferId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentScheduleCreateDto that = (PaymentScheduleCreateDto) o;
        return Objects.equals(creditOfferId, that.creditOfferId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditOfferId);
    }

    @Override
    public String toString() {
        return "PaymentScheduleCreateDto{" +
                "creditOfferId=" + creditOfferId +
                '}';
    }
}
