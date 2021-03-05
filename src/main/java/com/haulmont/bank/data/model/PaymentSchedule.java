package com.haulmont.bank.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "payment_schedules")
public class PaymentSchedule {

    @Id
    @GeneratedValue
    @Column(name = "payment_schedule_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "credit_offer_id")
    private CreditOffer creditOffer;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "amount_payment")
    private Double amountPayment;

    @Column(name = "repayment_amount_loan_body")
    private Double repaymentAmountLoanBody;

    @Column(name = "repayment_amount_percentages")
    private Double repaymentAmountPercentages;

    public PaymentSchedule() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CreditOffer getCreditOffer() {
        return creditOffer;
    }

    public void setCreditOffer(CreditOffer creditOffer) {
        this.creditOffer = creditOffer;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentSchedule that = (PaymentSchedule) o;
        return Objects.equals(id, that.id) && Objects.equals(creditOffer, that.creditOffer) && Objects.equals(date, that.date) && Objects.equals(amountPayment, that.amountPayment) && Objects.equals(repaymentAmountLoanBody, that.repaymentAmountLoanBody) && Objects.equals(repaymentAmountPercentages, that.repaymentAmountPercentages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditOffer, date, amountPayment, repaymentAmountLoanBody, repaymentAmountPercentages);
    }

    @Override
    public String toString() {
        return "PaymentSchedule{" +
                "id=" + id +
                ", creditOffer=" + creditOffer +
                ", date=" + date +
                ", amountPayment=" + amountPayment +
                ", repaymentAmountLoanBody=" + repaymentAmountLoanBody +
                ", repaymentAmountPercentages=" + repaymentAmountPercentages +
                '}';
    }
}
