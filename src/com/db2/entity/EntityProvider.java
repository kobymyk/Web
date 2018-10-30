package com.db2.entity;

import java.util.ArrayList;

// todo: EntityProvider - Persistent
public interface EntityProvider<R> {
    boolean addRecord(R record);

    ArrayList<R> getRecords();

    int size();
}
