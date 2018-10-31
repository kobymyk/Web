package com.db2.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class TaskEntity {
    private int id;
    private String name;
    private LocalDate dueDate;
    private int priority;
    // utils
    //private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    // map: db support
    public static TaskEntity fromCursor(ResultSet cursor) throws SQLException {
        TaskEntity record = new TaskEntity();
        // mapping
        record.setId(cursor.getInt("id"));
        record.setName(cursor.getString("name"));
        record.setPriority(cursor.getInt("priority"));
        // hard dependency: to_char(due_date, 'yyyy-MM-dd') as due_date
        String dueDateTimestamp = cursor.getString("due_date");
        record.setDueDate(LocalDate.parse(dueDateTimestamp, JdbcUtils.DATE_TIME_FORMATTER));

        return record;
    }

    public void setStatement(PreparedStatement statement) throws SQLException {
        statement.setString(1, this.name);
        statement.setDate(2, java.sql.Date.valueOf(this.dueDate));
        statement.setInt(3, this.priority);
    }
}
