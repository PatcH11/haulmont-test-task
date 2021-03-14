package com.haulmont.bank.data.mapstruct;

import com.haulmont.bank.data.dto.create.CreditCreateDto;
import com.haulmont.bank.data.dto.get.CreditGetDto;
import com.haulmont.bank.data.dto.update.CreditUpdateDto;
import com.haulmont.bank.data.model.Credit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CreditMapper extends BaseMapper<Credit, CreditGetDto, CreditCreateDto, CreditUpdateDto> {

    @Mapping(target = "id", ignore = true)
    public abstract Credit fromCreateDto(CreditCreateDto createDto);

    @Mapping(target = "loanLimit", ignore = true)
    @Mapping(target = "interestRate", ignore = true)
    public abstract Credit fromUpdateDto(CreditUpdateDto creditUpdateDto);
}
