package model;

import java.util.ArrayList;
import java.util.List;

public class School {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<SchoolClass> classes;

    public School() {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.classes = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void deleteStudent(Student student) {
        this.students.remove(student);
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void deleteTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
    }

    public List<SchoolClass> getClasses() {
        return classes;
    }

    public void addSchoolClass(SchoolClass schoolClass) {
        classes.add(schoolClass);
    }

    public void deleteSchoolClass(SchoolClass schoolClass) {
        classes.remove(schoolClass);
    }
}
