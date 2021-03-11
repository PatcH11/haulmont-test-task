package com.haulmont.bank.data.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "credit_offers")
public class CreditOffer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "credit_offer_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @OneToMany(mappedBy = "creditOffer")
    private List<PaymentSchedule> paymentSchedules;

    @Column(name = "credit_amount")
    private Double creditAmount;

    public CreditOffer() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public List<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

    public void setPaymentSchedules(List<PaymentSchedule> paymentSchedules) {
        this.paymentSchedules = paymentSchedules;
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
        CreditOffer that = (CreditOffer) o;
        return Objects.equals(id, that.id) && Objects.equals(client, that.client) && Objects.equals(credit, that.credit) && Objects.equals(paymentSchedules, that.paymentSchedules) && Objects.equals(creditAmount, that.creditAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, credit, paymentSchedules, creditAmount);
    }

    @Override
    public String toString() {
        return "CreditOffer{" +
                "id=" + id +
                ", client=" + client +
                ", credit=" + credit +
                ", paymentSchedules=" + paymentSchedules +
                ", creditAmount=" + creditAmount +
                '}';
    }
}
