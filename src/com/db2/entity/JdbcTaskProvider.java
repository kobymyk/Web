package com.db2.entity;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JdbcTaskProvider implements EntityProvider {
    private static final String SELECT_ALL_SQL = "SELECT id, name, dueDate, priority FROM  todo_list;";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean addRecord(Object record) {
        return false;
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:db.sqllite";
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }

    @Override
    public List getRecords() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);) {

            List<TaskEntity> todos = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int priority = resultSet.getInt("priority");
                String dueDateTimestamp = resultSet.getString("dueDate");
                LocalDate dueDate = LocalDate.parse(dueDateTimestamp, DATE_TIME_FORMATTER);


                TaskEntity todo = new TaskEntity(id, name, dueDate, priority);
                todos.add(todo);

            }
            return todos;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        return 0;
    }
}
