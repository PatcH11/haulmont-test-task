package com.haulmont.bank.data.mapstruct;

import com.haulmont.bank.data.dto.create.PaymentScheduleCreateDto;
import com.haulmont.bank.data.dto.get.PaymentScheduleGetAndUpdateDto;
import com.haulmont.bank.data.model.PaymentSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PaymentScheduleMapper extends BaseMapper<PaymentSchedule, PaymentScheduleGetAndUpdateDto, PaymentScheduleCreateDto> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creditOffer.id", source = "creditOfferId")
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "amountPayment", ignore = true)
    @Mapping(target = "repaymentAmountLoanBody", ignore = true)
    @Mapping(target = "repaymentAmountPercentages", ignore = true)
    @Mapping(target = "indebtedness", ignore = true)
    public abstract PaymentSchedule fromCreateDto(PaymentScheduleCreateDto createDto);
}
