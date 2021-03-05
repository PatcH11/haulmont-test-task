package com.haulmont.bank.data.mapstruct;

import com.haulmont.bank.data.dto.CreditDto;
import com.haulmont.bank.data.model.Credit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CreditMapper extends BaseMapper<Credit, CreditDto> {
}
