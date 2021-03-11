package com.haulmont.bank.data.mapstruct;

import com.haulmont.bank.data.dto.create.CreditOfferCreateDto;
import com.haulmont.bank.data.dto.get.CreditOfferGetAndUpdateDto;
import com.haulmont.bank.data.model.CreditOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CreditOfferMapper extends BaseMapper<CreditOffer, CreditOfferGetAndUpdateDto, CreditOfferCreateDto> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    @Mapping(target = "credit.id", source = "creditId")
    public abstract CreditOffer fromCreateDto(CreditOfferCreateDto createDto);
}
