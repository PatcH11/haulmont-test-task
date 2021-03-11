package com.haulmont.bank.data.dto.get;

import java.util.Objects;
import java.util.UUID;

public class CreditGetAndUpdateDto {

    private UUID id;
    private String name;
    private Long loanLimit;
    private Double interestRate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditGetAndUpdateDto that = (CreditGetAndUpdateDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(loanLimit, that.loanLimit) && Objects.equals(interestRate, that.interestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, loanLimit, interestRate);
    }

    @Override
    public String toString() {
        return "CreditGetAndUpdateDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loanLimit=" + loanLimit +
                ", interestRate=" + interestRate +
                '}';
    }
}
