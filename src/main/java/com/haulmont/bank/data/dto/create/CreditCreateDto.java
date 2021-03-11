package com.haulmont.bank.data.dto.create;

import java.util.Objects;

public class CreditCreateDto {

    private String name;
    private Long loanLimit;
    private Double interestRate;

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
        CreditCreateDto that = (CreditCreateDto) o;
        return Objects.equals(name, that.name) && Objects.equals(loanLimit, that.loanLimit) && Objects.equals(interestRate, that.interestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, loanLimit, interestRate);
    }

    @Override
    public String toString() {
        return "CreditCreateDto{" +
                "name='" + name + '\'' +
                ", loanLimit=" + loanLimit +
                ", interestRate=" + interestRate +
                '}';
    }
}
