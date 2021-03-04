package com.haulmont.bank.data.mapstruct;

import com.haulmont.bank.data.dto.ClientDto;
import com.haulmont.bank.data.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ClientMapper extends BaseMapper<Client, ClientDto> {
}
