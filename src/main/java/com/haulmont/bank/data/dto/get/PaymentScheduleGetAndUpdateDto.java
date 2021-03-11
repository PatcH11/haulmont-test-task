package com.haulmont.bank.data.dto.get;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PaymentScheduleGetAndUpdateDto {

    private UUID id;
    private CreditOfferGetAndUpdateDto creditOffer;
    private Date date;
    private Double amountPayment;
    private Double repaymentAmountLoanBody;
    private Double repaymentAmountPercentages;
    private Double indebtedness;

    public PaymentScheduleGetAndUpdateDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CreditOfferGetAndUpdateDto getCreditOffer() {
        return creditOffer;
    }

    public void setCreditOffer(CreditOfferGetAndUpdateDto creditOffer) {
        this.creditOffer = creditOffer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Double getIndebtedness() {
        return indebtedness;
    }

    public void setIndebtedness(Double indebtedness) {
        this.indebtedness = indebtedness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentScheduleGetAndUpdateDto that = (PaymentScheduleGetAndUpdateDto) o;
        return Objects.equals(id, that.id) && Objects.equals(creditOffer, that.creditOffer) && Objects.equals(date, that.date) && Objects.equals(amountPayment, that.amountPayment) && Objects.equals(repaymentAmountLoanBody, that.repaymentAmountLoanBody) && Objects.equals(repaymentAmountPercentages, that.repaymentAmountPercentages) && Objects.equals(indebtedness, that.indebtedness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditOffer, date, amountPayment, repaymentAmountLoanBody, repaymentAmountPercentages, indebtedness);
    }

    @Override
    public String toString() {
        return "PaymentScheduleGetAndUpdateDto{" +
                "id=" + id +
                ", creditOffer=" + creditOffer +
                ", date=" + date +
                ", amountPayment=" + amountPayment +
                ", repaymentAmountLoanBody=" + repaymentAmountLoanBody +
                ", repaymentAmountPercentages=" + repaymentAmountPercentages +
                ", indebtedness=" + indebtedness +
                '}';
    }
}
