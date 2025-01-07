package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:school.db";

    public static Connection connect() {
        try {
            Connection connection =  DriverManager.getConnection(DATABASE_URL);
            Statement statement = connection.createStatement();
            statement.execute("PRAGMA foreign_keys = ON;");
            return connection;
        } catch (SQLException e) {
            System.err.println("database connection error : " + e.getMessage());
            return null;
        }
    }

    public static void initializeDatabase() {
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            String createStudentsTable = """
                    CREATE TABLE IF NOT EXISTS students (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        age INTIGER NOT NULL
                    )
                    """;
            statement.execute(createStudentsTable);
            String createTeachersTable = """
                    CREATE TABLE IF NOT EXISTS teachers (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        subject TEXT NOT NULL
                    )
                    """;
            statement.execute(createTeachersTable);
            String createClassesTable = """
                    CREATE TABLE IF NOT EXISTS classes (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        teacher_id INTEGER NOT NULL,
                        FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE RESTRICT
                    )
                    """;
            statement.execute(createClassesTable);
            String createStudentClassTable = """
                    CREATE TABLE IF NOT EXISTS student_classes (
                        student_id INTEGER NOT NULL,
                        class_id INTEGER NOT NULL,
                        PRIMARY KEY(student_id,class_id),
                        FOREIGN KEY (student_id) REFERENCES students(id),
                        FOREIGN KEY (class_id) REFERENCES classes(id)
                    )
                    """;
            statement.execute(createStudentClassTable);
            System.out.println("Database intialized successfully");
        } catch (SQLException e) {
            System.err.println("Database intialization error : " + e.getMessage());
        }
    }
}