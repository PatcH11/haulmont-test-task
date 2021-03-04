package com.haulmont.bank.data.repository;

import com.haulmont.bank.data.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
