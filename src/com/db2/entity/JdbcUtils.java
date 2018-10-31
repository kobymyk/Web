package com.db2.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

// enum?
// looks like constants currently
public final class JdbcUtils {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // ANSI
    public static final String SQL_SELECT_ALL = "SELECT id, name, to_char(due_date, 'yyyy-MM-dd') as due_date, priority FROM todo_list t";
    public static final String SQL_SELECT_ROW = "SELECT id, name, dueDate, priority FROM todo_list;";
    public static final String DML_INSERT = "INSERT INTO todo_list (id, name, due_date, priority) select HIBERNATE_SEQUENCE.nextval, ?, ?, ? FROM dual";
    // ORA ! null = ''
    public static final String PL_PIPE_ALL = "SELECT id, name, dueDate, priority FROM todo_list;";

    public static final String PL_LOAD_TAB = "SELECT id, name, dueDate, priority FROM todo_list;";
    public static final String PL_GET_TAB = "SELECT id, name, dueDate, priority FROM todo_list;";
    // cfg parameters
    public static String getUrl(String user, String password) { return "jdbc:oracle:thin:scott/tiger@//localhost:1521/XE"; }
    public static String DB_USER = "scott";
    public static String DB_PWD = "tiger";

    // utils
    public static Connection getConnection(String url) throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }
}
