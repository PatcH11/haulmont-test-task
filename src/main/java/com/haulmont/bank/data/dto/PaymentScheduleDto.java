package com.haulmont.bank.data.dto;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class PaymentScheduleDto {

    private UUID id;
    private CreditOfferDto creditOffer;
    private Timestamp data;
    private Double amountPayment;
    private Double repaymentAmountLoanBody;
    private Double repaymentAmountPercentages;

    public PaymentScheduleDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CreditOfferDto getCreditOffer() {
        return creditOffer;
    }

    public void setCreditOffer(CreditOfferDto creditOffer) {
        this.creditOffer = creditOffer;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Double getAmountPayment() {
        return amountPayment;
    }

    public void setAmountPayment(Double amountPayment) {
        this.amountPayment = amountPayment;
    }

    public Double getRepaymentAmountLoanBody() {
        return repaymentAmountLoanBody;
    }

    public void setRepaymentAmountLoanBody(Double repaymentAmountLoanBody) {
        this.repaymentAmountLoanBody = repaymentAmountLoanBody;
    }

    public Double getRepaymentAmountPercentages() {
        return repaymentAmountPercentages;
    }

    public void setRepaymentAmountPercentages(Double repaymentAmountPercentages) {
        this.repaymentAmountPercentages = repaymentAmountPercentages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentScheduleDto that = (PaymentScheduleDto) o;
        return Objects.equals(id, that.id) && Objects.equals(creditOffer, that.creditOffer) && Objects.equals(data, that.data) && Objects.equals(amountPayment, that.amountPayment) && Objects.equals(repaymentAmountLoanBody, that.repaymentAmountLoanBody) && Objects.equals(repaymentAmountPercentages, that.repaymentAmountPercentages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditOffer, data, amountPayment, repaymentAmountLoanBody, repaymentAmountPercentages);
    }

    @Override
    public String toString() {
        return "PaymentScheduleDto{" +
                "id=" + id +
                ", creditOffer=" + creditOffer +
                ", data=" + data +
                ", amountPayment=" + amountPayment +
                ", repaymentAmountLoanBody=" + repaymentAmountLoanBody +
                ", repaymentAmountPercentages=" + repaymentAmountPercentages +
                '}';
    }
}
