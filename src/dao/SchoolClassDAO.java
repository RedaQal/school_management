package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;
import model.SchoolClass;
import model.Student;
import model.Teacher;

public class SchoolClassDAO implements GeniricDAO<SchoolClass, Integer> {
    private TeacherDAO teacherDAO;
    private StudentDAO studentDAO;

    public SchoolClassDAO() {
        this.teacherDAO = new TeacherDAO();
        this.studentDAO = new StudentDAO();
    }

    @Override
    public void save(SchoolClass schoolClass) {
        String saveString = "INSERT INTO classes(name,teacher_id) VALUES(?,?)";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statement = connection.prepareStatement(saveString)) {
            statement.setString(1, schoolClass.getClassName());
            statement.setInt(2, schoolClass.getTeacher().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
        }
    }

    @Override
    public SchoolClass findById(Integer id) {
        String findByIdString = "SELECT * FROM classes WHERE id = ?";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statement = connection.prepareStatement(findByIdString)) {
            statement.setInt(1, id);
            ResultSet rst = statement.executeQuery();
            if (rst.next()) {
                Teacher teacher = teacherDAO.findById(rst.getInt("teacher_id"));
                SchoolClass schoolClass = new SchoolClass(rst.getInt("id"), rst.getString("name"), teacher);
                return schoolClass;
            }

        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<SchoolClass> findAll() {
        String findAllString = "SELECT * FROM classes";
        try (Connection connection = DatabaseManager.connect(); Statement statement = connection.createStatement()) {
            ResultSet rst = statement.executeQuery(findAllString);
            List<SchoolClass> schoolClasses = new ArrayList<>();
            while (rst.next()) {
                Teacher teacher = teacherDAO.findById(rst.getInt("teacher_id"));
                SchoolClass schoolClass = new SchoolClass(rst.getInt("id"), rst.getString("name"), teacher);
                schoolClasses.add(schoolClass);
            }
            return schoolClasses;
        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(SchoolClass schoolClass) {
        String updateString = "UPDATE classes SET name = ? , teacher_id = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statement = connection.prepareStatement(updateString)) {
            statement.setString(1, schoolClass.getClassName());
            statement.setInt(2, schoolClass.getTeacher().getId());
            statement.setInt(3, schoolClass.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("error : " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        String deleteString = "DELETE FROM classes WHERE id = ?";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statement = connection.prepareStatement(deleteString)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("error : " + e.getMessage());
        }
    }

    public void addStudentToClass(Student student, SchoolClass schoolClass) {
        String addString = "INSERT INTO student_classes VALUES(?,?)";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statement = connection.prepareStatement(addString)) {
            statement.setInt(1, student.getId());
            statement.setInt(2, schoolClass.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("error : " + e.getMessage());
        }
    }

    public void deleteStudentFromClass(Student student,SchoolClass schoolClass){
        String deleteString = "DELETE FROM student_classes WHERE student_id = ? AND class_id = ?";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statement = connection.prepareStatement(deleteString)) {
            statement.setInt(1, student.getId());
            statement.setInt(2, schoolClass.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("error : " + e.getMessage());
        }
    }

    public SchoolClass findStudentsOfClass(SchoolClass schoolClass){
        String findString = "SELECT * FROM student_classes WHERE class_id = ?";
        try (Connection connection = DatabaseManager.connect();
                PreparedStatement statement = connection.prepareStatement(findString)) {
            statement.setInt(1, schoolClass.getId());
            ResultSet rst = statement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (rst.next()) {
                Student student = studentDAO.findById(rst.getInt("student_id"));
                students.add(student);
            }
            schoolClass.setStudents(students);
            return schoolClass;
        } catch (Exception e) {
            System.err.println("error : " + e.getMessage());
        }
        return null;
    }

}
