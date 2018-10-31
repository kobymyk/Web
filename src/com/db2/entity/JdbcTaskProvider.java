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

            List<TaskEntity> records = new ArrayList<>();

            while (resultSet.next()) {
                TaskEntity record = new TaskEntity();

                record.setId(resultSet.getInt("id"));
                record.setName(resultSet.getString("name"));
                record.setPriority(resultSet.getInt("priority"));
                String dueDateTimestamp = resultSet.getString("dueDate");
                record.setDueDate(LocalDate.parse(dueDateTimestamp, DATE_TIME_FORMATTER));

                records.add(record);
            }
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getRecord(int id) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
