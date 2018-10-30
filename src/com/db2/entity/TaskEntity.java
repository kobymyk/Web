package com.db2.entity;

import java.time.LocalDate;

public class TaskEntity {
    private int id;
    private String name;
    private LocalDate dueDate;
    private int priority;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public TaskEntity(int id, String name, LocalDate dueDate, int priority) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public TaskEntity() {
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", name=" + name +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                '}';
    }
}
