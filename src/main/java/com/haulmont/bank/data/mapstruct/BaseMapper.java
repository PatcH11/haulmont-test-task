package com.haulmont.bank.data.mapstruct;

import java.util.List;

public abstract class BaseMapper<E, GU, C> {

    public abstract GU toGetAndUpdateDto(E entity);

    public abstract E fromCreateDto(C createDto);

    public abstract List<GU> toGetDto(Iterable<E> entities);
}
