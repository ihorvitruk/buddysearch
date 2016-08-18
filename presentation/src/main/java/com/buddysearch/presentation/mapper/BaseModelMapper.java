package com.buddysearch.presentation.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseModelMapper<DTO, MODEL> {

    public abstract MODEL map(DTO dto);

    public List<MODEL> map(Collection<DTO> dtoList) {
        List<MODEL> models = null;
        if (dtoList != null) {
            models = new ArrayList<>();
            MODEL model;
            for (DTO dto : dtoList) {
                model = map(dto);
                models.add(model);
            }
        }
        return models;
    }
}
