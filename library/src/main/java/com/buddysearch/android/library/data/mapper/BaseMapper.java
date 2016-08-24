package com.buddysearch.android.library.data.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseMapper<FROM, TO> {

    public abstract TO map(FROM from);

    public List<TO> map(Collection<FROM> fromList) {
        List<TO> toList = null;
        if (fromList != null) {
            toList = new ArrayList<>();
            TO to;
            for (FROM FROM : fromList) {
                to = map(FROM);
                toList.add(to);
            }
        }
        return toList;
    }
}
