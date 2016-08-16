package com.buddysearch.presentation.data.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseEntityMapper<DTO, ENTITY> {

    public abstract DTO map(ENTITY entity);

    public List<DTO> map(Collection<ENTITY> entities) {
        List<DTO> dtoList = null;
        if (entities != null) {
            dtoList = new ArrayList<>();
            DTO dto;
            for (ENTITY entity : entities) {
                dto = map(entity);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
}
