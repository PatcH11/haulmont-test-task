package com.haulmont.bank.data.mapstruct;

import com.haulmont.bank.data.dto.create.CreditCreateDto;
import com.haulmont.bank.data.dto.get.CreditGetAndUpdateDto;
import com.haulmont.bank.data.model.Credit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CreditMapper extends BaseMapper<Credit, CreditGetAndUpdateDto, CreditCreateDto> {

    @Mapping(target = "id", ignore = true)
    public abstract Credit fromCreateDto(CreditCreateDto createDto);
}
