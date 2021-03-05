package com.haulmont.bank.data.mapstruct;

import com.haulmont.bank.data.dto.CreditOfferDto;
import com.haulmont.bank.data.model.CreditOffer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CreditOfferMapper extends BaseMapper<CreditOffer, CreditOfferDto> {
}
