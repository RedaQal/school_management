package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;
import model.Student;

public class StudentDAO implements GeniricDAO<Student, Integer> {

    @Override
    public void save(Student student) {
        String saveString = "INSERT INTO students(name,age) VALUES(?,?)";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statment = connection.prepareStatement(saveString)) {
            statment.setString(1, student.getName());
            statment.setInt(2, student.getAge());
            statment.executeUpdate();
        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
        }
    }

    @Override
    public Student findById(Integer id) {
        String findByIdString = "SELECT * FROM students WHERE id = ?";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statment = connection.prepareStatement(findByIdString)) {
            statment.setInt(1, id);
            ResultSet rst = statment.executeQuery();
            if (rst.next()) {
                Student student = new Student(rst.getInt("id"), rst.getString("name"), rst.getInt("age"));
                return student;
            }
            return null;
        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Student> findAll() {
        String findAllString = "SELECT * FROM students";
        try (Connection connection = DatabaseManager.connect();
                Statement statment = connection.createStatement()) {
            ResultSet rst = statment.executeQuery(findAllString);
            List<Student> studenList = new ArrayList<>();
            while (rst.next()) {
                Student student = new Student(rst.getInt("id"), rst.getString("name"), rst.getInt("age"));
                studenList.add(student);
            }
            return studenList;
        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
            return null;
        }
    }

    @Override
    public void update(Student student) {
        String updateString = "UPDATE students SET name = ?,age = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statment = connection.prepareStatement(updateString)) {
            statment.setString(1, student.getName());
            statment.setInt(2, student.getAge());
            statment.setInt(3, student.getId());
            statment.executeUpdate();

        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String deleteString = "DELETE FROM students WHERE id = ?";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statement = connection.prepareStatement(deleteString)) {
                    statement.setInt(1, id);
                    statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("errror : " + e.getMessage());
        }
    }

}
