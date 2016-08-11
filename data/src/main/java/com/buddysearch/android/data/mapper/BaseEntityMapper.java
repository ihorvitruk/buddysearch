package com.buddysearch.android.data.mapper;

import java.util.Collection;
import java.util.List;

public abstract class BaseEntityMapper<DTO, ENTITY> {

    public abstract DTO transform(ENTITY entity);

    public abstract List<DTO> transform(Collection<ENTITY> entities);
}
