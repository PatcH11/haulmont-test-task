package com.haulmont.bank.data.mapstruct;

import com.haulmont.bank.data.dto.PaymentScheduleDto;
import com.haulmont.bank.data.model.PaymentSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PaymentScheduleMapper extends BaseMapper<PaymentSchedule, PaymentScheduleDto> {
}
