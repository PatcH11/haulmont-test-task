package com.haulmont.bank.data.repository;

import com.haulmont.bank.data.model.Client;
import com.haulmont.bank.data.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findByCreditsNotContains(Credit credit);
}
