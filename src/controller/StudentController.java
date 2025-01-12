package controller;

import java.util.Scanner;

import model.School;
import model.Student;
import view.StudentView;

public class StudentController {
    private School school;
    private StudentView view;
    private Scanner scanner;

    public StudentController() {
        this.school = School.getInstance();
        this.view = new StudentView();
        this.scanner = new Scanner(System.in);
    }

    private Student findStudent(int id) {
        Student student = school.getStudents().stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        return student;
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
                    view.displayStudents(school.getStudents());
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please choose a valid option");
                    break;
            }
        } while (entry != 5);
    }

    private void addStudent() {
        System.out.println("Enter the Student Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (findStudent(id) != null) {
            System.out.println("A student with this Id already exists");
            return;
        }
        System.out.println("Enter the student name :");
        String name = scanner.nextLine();
        System.out.println("Enter the student age :");
        int age = scanner.nextInt();
        Student student = new Student(id, name, age);
        school.addStudent(student);
        System.out.println("student added successfuly");
    }

    private void updateStudent() {
        System.out.println("enter the student id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student student = findStudent(id);
        if (student != null) {
            System.out.println("enter the new name :");
            String name = scanner.nextLine();
            System.out.println("enter the new age :");
            int age = scanner.nextInt();
            scanner.nextLine();
            student.setName(name);
            student.setAge(age);
            System.out.println("student updated successfully");
        } else {
            System.out.println("Student not found");
        }
    }

    private void deleteStudent() {
        System.out.print("Enter the Student Id : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student studentToRemove = findStudent(id);

        if (studentToRemove != null) {
            school.deleteStudent(studentToRemove);
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found");
        }
    }

}
