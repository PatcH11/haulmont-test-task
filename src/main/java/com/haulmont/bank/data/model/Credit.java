package com.haulmont.bank.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "credits")
public class Credit {

    @Id
    @GeneratedValue
    @Column(name = "credit_id")
    private UUID id;

    @Column(name = "loan_limit")
    private Long loanLimit;

    @Column(name = "interest_rate")
    private Double interestRate;

    @ManyToMany(mappedBy = "credits")
    private Set<Client> clients;

    public Credit() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return Objects.equals(id, credit.id) && Objects.equals(loanLimit, credit.loanLimit) && Objects.equals(interestRate, credit.interestRate) && Objects.equals(clients, credit.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loanLimit, interestRate, clients);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", loanLimit=" + loanLimit +
                ", interestRate=" + interestRate +
                ", clients=" + clients +
                '}';
    }
}
