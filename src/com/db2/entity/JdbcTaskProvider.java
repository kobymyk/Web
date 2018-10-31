package com.db2.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 executes query/dml
 map data from DB to Entity
 todo: able to process any Entity -> E.map
*/
public class JdbcTaskProvider implements EntityProvider {
    // has connection
    // private static Connection connection;

    private Connection getConnection() throws SQLException {
        // if not connected
        return JdbcUtils.getConnection("jdbc:oracle:thin:scott/tiger@//localhost:1521/XE");
    }

    @Override
    public boolean addRecord(Object record) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(JdbcUtils.DML_INSERT);) {

            ((TaskEntity) record).setStatement(statement);
            statement.executeUpdate();

            return true; //
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List getRecords() {
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
             // getResultSet(String sql): ResultSet
             ResultSet cursor = statement.executeQuery(JdbcUtils.SQL_SELECT_ALL);) {

            // not able to obtain cursor size before fetch
            List<TaskEntity> records = new ArrayList<>();
            while (cursor.next()) {
                // mapping
                TaskEntity record = TaskEntity.fromCursor(cursor);
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

    //@Override
    public int size() {
        // not able to obtain cursor size before fetch
        // ora cursor/stream behavior
        return 0;
    }
}
