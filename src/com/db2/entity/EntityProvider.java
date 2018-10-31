package com.db2.entity;

import java.util.List;

public interface EntityProvider<R> {
    boolean addRecord(R record);

    //ArrayList<R> getRecords();
    List<R> getRecords();

    R getRecord(int id);

    int size();
}
