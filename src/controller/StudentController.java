package controller;

import java.util.Scanner;

import dao.StudentDAO;
import model.Student;
import view.StudentView;

public class StudentController {
    private StudentView view;
    private Scanner scanner;
    private StudentDAO studentDAO;

    public StudentController() {
        this.studentDAO = new StudentDAO();
        this.view = new StudentView();
        this.scanner = new Scanner(System.in);
    }

    public void manageStudent() {
        int entry;
        do {
            view.displayStudentMenu();
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    view.displayStudents(studentDAO.findAll());
                    break;
                case 5:
                    System.out.println("quite");
                    break;
                default:
                    break;
            }
        } while (entry != 5);
    }

    private void addStudent() {
        System.out.print("Enter the student name :");
        String name = scanner.nextLine();
        System.out.print("Enter the student age :");
        int age = scanner.nextInt();
        Student student = new Student(name, age);
        studentDAO.save(student);
    }

    private void updateStudent() {
        System.out.print("enter the student id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student student = studentDAO.findById(id);
        if (student != null) {
            System.out.print("enter the new name :");
            String name = scanner.nextLine();
            System.out.print("enter the new age :");
            int age = scanner.nextInt();
            scanner.nextLine();
            student.setName(name);
            student.setAge(age);
            studentDAO.update(student);
            System.out.println("student updated successfully");
        } else {
            System.out.println("student unfound");
        }
    }

    private void deleteStudent() {
        System.out.print("Enter the student Id :");
        int id = scanner.nextInt();
        Student student = studentDAO.findById(id);
        if (student != null) {
            studentDAO.delete(student.getId());
            System.out.println("student deleted successfully");
        } else {
            System.out.println("student unfound");
        }
    }
}
