package com.buddysearch.android.library.data.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper<T1, T2> {

    public abstract T1 map1(T2 o2);

    public abstract T2 map2(T1 o1);

    public List<T1> map1(List<T2> o2List) {
        List<T1> o1List = null;
        if (o2List != null) {
            o1List = new ArrayList<>();
            T1 o1;
            for (T2 o2 : o2List) {
                o1 = map1(o2);
                o1List.add(o1);
            }
        }
        return o1List;
    }

    public List<T2> map2(List<T1> o1List) {
        List<T2> o2List = null;
        if (o1List != null) {
            o2List = new ArrayList<>();
            T2 o2;
            for (T1 o1 : o1List) {
                o2 = map2(o1);
                o2List.add(o2);
            }
        }
        return o2List;
    }
}
