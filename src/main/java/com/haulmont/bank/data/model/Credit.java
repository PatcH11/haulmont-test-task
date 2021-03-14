package com.haulmont.bank.data.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "credits")
public class Credit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "credit_id")
    private UUID id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "loan_limit")
    private Long loanLimit;

    @Column(name = "interest_rate")
    private Double interestRate;

    @ManyToMany(mappedBy = "credits")
    private List<Client> clients = new ArrayList<>();

    public Credit() {
    }

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

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return Objects.equals(id, credit.id) && Objects.equals(name, credit.name) && Objects.equals(loanLimit, credit.loanLimit) && Objects.equals(interestRate, credit.interestRate) && Objects.equals(clients, credit.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, loanLimit, interestRate, clients);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loanLimit=" + loanLimit +
                ", interestRate=" + interestRate +
                ", clients=" + clients +
                '}';
    }
}
