package com.db2.entity;

import java.util.List;

// aka DAO interface
public interface EntityProvider<R> {
    // insert
    boolean addRecord(R record);

    //ArrayList<R> getRecords();
    // get all records
    List<R> getRecords();
    // scalar query by PK
    R getRecord(int id);

    //int size();
}
