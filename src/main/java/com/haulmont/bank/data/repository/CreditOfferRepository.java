package com.haulmont.bank.data.repository;

import com.haulmont.bank.data.model.Client;
import com.haulmont.bank.data.model.Credit;
import com.haulmont.bank.data.model.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CreditOfferRepository extends JpaRepository<CreditOffer, UUID> {

    List<CreditOffer> findAllByClientIs(Client client);

    CreditOffer findByClientIsAndCreditIs(Client client, Credit credit);
}
