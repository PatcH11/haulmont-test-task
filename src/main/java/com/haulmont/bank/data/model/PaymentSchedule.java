package com.haulmont.bank.data.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "payment_schedules")
public class PaymentSchedule {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "payment_schedule_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "credit_offer_id")
    private CreditOffer creditOffer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "date",
            nullable = false,
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP"
    )
    private Date date = new Date();

    @Column(name = "amount_payment")
    private Double amountPayment;

    @Column(name = "repayment_amount_loan_body")
    private Double repaymentAmountLoanBody;

    @Column(name = "repayment_amount_percentages")
    private Double repaymentAmountPercentages;

    @Column(name = "indebtedness")
    private Double indebtedness;

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
        PaymentSchedule that = (PaymentSchedule) o;
        return Objects.equals(id, that.id) && Objects.equals(creditOffer, that.creditOffer) && Objects.equals(date, that.date) && Objects.equals(amountPayment, that.amountPayment) && Objects.equals(repaymentAmountLoanBody, that.repaymentAmountLoanBody) && Objects.equals(repaymentAmountPercentages, that.repaymentAmountPercentages) && Objects.equals(indebtedness, that.indebtedness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditOffer, date, amountPayment, repaymentAmountLoanBody, repaymentAmountPercentages, indebtedness);
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
                ", indebtedness=" + indebtedness +
                '}';
    }
}
