package com.haulmont.bank.data.repository;

import com.haulmont.bank.data.model.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditOfferRepository extends JpaRepository<CreditOffer, UUID> {
}
