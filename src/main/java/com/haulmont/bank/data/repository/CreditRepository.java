package com.haulmont.bank.data.repository;

import com.haulmont.bank.data.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {
}
