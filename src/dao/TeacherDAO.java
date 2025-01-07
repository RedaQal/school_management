package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;
import model.Teacher;

public class TeacherDAO implements GeniricDAO<Teacher, Integer> {

    @Override
    public void save(Teacher teacher) {
        String saveString = "INSERT INTO teachers(name,subject) VALUES(?,?)";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statment = connection.prepareStatement(saveString)) {
            statment.setString(1, teacher.getName());
            statment.setString(2, teacher.getSubject());
            statment.executeUpdate();
        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
        }
    }

    @Override
    public Teacher findById(Integer id) {
        String findByIdString = "SELECT * FROM teachers WHERE id = ?";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statment = connection.prepareStatement(findByIdString)) {
            statment.setInt(1, id);
            ResultSet rst = statment.executeQuery();
            if (rst.next()) {
                Teacher teacher = new Teacher(rst.getInt("id"), rst.getString("name"), rst.getString("subject"));
                return teacher;
            }
        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        String findByIdString = "SELECT * FROM teachers";
        try (Connection connection = DatabaseManager.connect();
                Statement statment = connection.createStatement()) {
            ResultSet rst = statment.executeQuery(findByIdString);
            List<Teacher> teachers = new ArrayList<>();
            while (rst.next()) {
                Teacher teacher = new Teacher(rst.getInt("id"), rst.getString("name"), rst.getString("subject"));
                teachers.add(teacher);
            }
            return teachers;
        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
            return null;
        }
    }

    @Override
    public void update(Teacher teacher) {
        String updateString = "UPDATE teachers SET name = ? , subject = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statement = connection.prepareStatement(updateString)) {
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getSubject());
            statement.setInt(3, teacher.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String deleteString = "DELETE FROM teachers WHERE id = ?";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statement = connection.prepareStatement(deleteString)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
        }
    }

}
