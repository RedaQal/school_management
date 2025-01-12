package view;

import java.util.List;

import model.Student;

public class StudentView {
    public void displayStudentMenu() {
        System.out.println("|-------Student Menu-------|");
        System.out.println("1- add a student");
        System.out.println("2- update student");
        System.out.println("3- delete student");
        System.out.println("4- show students");
        System.out.println("5- quitte");
    }

    public void displayStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("There is no students");
        } else {
            System.out.println("-------Student List-------");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }
}
