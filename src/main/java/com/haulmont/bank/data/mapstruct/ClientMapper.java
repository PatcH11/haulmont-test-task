package com.haulmont.bank.data.mapstruct;

import com.haulmont.bank.data.dto.create.ClientCreateDto;
import com.haulmont.bank.data.dto.get.ClientGetDto;
import com.haulmont.bank.data.dto.update.ClientUpdateDto;
import com.haulmont.bank.data.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ClientMapper extends BaseMapper<Client, ClientGetDto, ClientCreateDto, ClientUpdateDto> {

    @Mapping(target = "id", ignore = true)
    public abstract Client fromCreateDto(ClientCreateDto createDto);
}
