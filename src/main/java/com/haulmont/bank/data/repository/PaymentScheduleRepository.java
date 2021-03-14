package com.haulmont.bank.data.repository;

import com.haulmont.bank.data.model.CreditOffer;
import com.haulmont.bank.data.model.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, UUID> {

    PaymentSchedule findFirstByCreditOfferOrderByDateDesc(CreditOffer creditOffer);

    List<PaymentSchedule> findByCreditOfferOrderByDate(CreditOffer creditOffer);

    void deleteByCreditOffer(CreditOffer creditOffer);
}
