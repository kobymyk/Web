package com.db2.entity;

import java.time.LocalDate;
import java.util.*;

// mock DAO
public class TaskEntityProvider implements EntityProvider {
    // todo: singleton TaskEntityProvider instance;
    // records
    private ArrayList<TaskEntity> records;

    public TaskEntityProvider() {
        records = getDefaultRecords();
    }

    @Override
    public ArrayList<TaskEntity> getRecords() {
        return records;
    }

    @Override
    public TaskEntity getRecord(int id) {
        // get by index should be changed
        return records.get(id);
    }

    @Override
    public boolean addRecord(Object record) {
        TaskEntity item = (TaskEntity) record;
        // increment
        item.setId(records.size() + 1);
        return records.add((TaskEntity) record);
    }

    private ArrayList<TaskEntity> getDefaultRecords() {
        ArrayList<TaskEntity> result = new ArrayList<>();
        // mock load
        result.add(new TaskEntity(1, "Task 1", LocalDate.of(2018, 11, 10), 1));
        result.add(new TaskEntity(2, "Task 2", LocalDate.of(2018, 12, 21), 2));
        result.add(new TaskEntity(3, "Task 3", LocalDate.of(2018, 12, 21), 2));
        result.add(new TaskEntity(4, "Task 4", LocalDate.of(2019, 1, 1), 4));

        return result;
    }

    public int size() {
        return records.size();
    }
}
