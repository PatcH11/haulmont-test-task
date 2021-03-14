package com.haulmont.bank.data.mapstruct;

import java.util.List;

public abstract class BaseMapper<E, G, C, U> {

    public abstract E fromGetDto(G getDto);

    public abstract G toGetDto(E entity);

    public abstract E fromUpdateDto(U updateDto);

    public abstract E fromCreateDto(C createDto);

    public abstract List<G> toGetDto(Iterable<E> entities);
}
