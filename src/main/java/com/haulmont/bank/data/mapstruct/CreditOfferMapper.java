package com.haulmont.bank.data.mapstruct;

import com.haulmont.bank.data.dto.create.CreditOfferCreateDto;
import com.haulmont.bank.data.dto.get.CreditOfferGetDto;
import com.haulmont.bank.data.dto.update.CreditOfferUpdateDto;
import com.haulmont.bank.data.model.CreditOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CreditOfferMapper extends BaseMapper<CreditOffer, CreditOfferGetDto, CreditOfferCreateDto, CreditOfferUpdateDto> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    @Mapping(target = "credit.id", source = "creditId")
    public abstract CreditOffer fromCreateDto(CreditOfferCreateDto createDto);

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "credit", ignore = true)
    public abstract CreditOffer fromUpdateDto(CreditOfferUpdateDto creditOfferUpdateDto);
}
