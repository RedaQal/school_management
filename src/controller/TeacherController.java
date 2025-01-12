package controller;

import java.util.Scanner;

import model.School;
import model.Teacher;
import view.TeacherView;

public class TeacherController {
    private School school;
    private TeacherView view;
    private Scanner scanner;

    public TeacherController() {
        this.school = School.getInstance();
        this.view = new TeacherView();
        this.scanner = new Scanner(System.in);
    }

    private Teacher findTeacher(int id) {
        Teacher teacher = school.getTeachers().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        return teacher;

    }

    public void manageTeacher() {
        int entry;
        do {
            view.displayTeacherMenu();
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry) {
                case 1:
                    addTeacher();
                    break;
                case 2:
                    updateTeacher();
                    break;
                case 3:
                    deleteTeacher();
                    break;
                case 4:
                    view.displayTeachers(school.getTeachers());
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please choose a valid option");
                    break;
            }
        } while (entry != 5);
    }

    private void addTeacher() {
        System.out.println("Enter the Teacher Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (findTeacher(id) != null) {
            System.out.println("A teacher with this Id already exists");
            return;
        }
        System.out.println("Enter the Teacher name :");
        String name = scanner.nextLine();
        System.out.println("Enter the Teacher subject :");
        String subject = scanner.nextLine();
        Teacher teacher = new Teacher(id, name, subject);
        school.addTeacher(teacher);
        System.out.println("Teacher added successfully");
    }

    private void updateTeacher() {
        System.out.println("enter the Teacher id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        Teacher teacher = findTeacher(id);
        if (teacher != null) {
            System.out.println("enter the new name :");
            String name = scanner.nextLine();
            System.out.println("enter the new subject :");
            String subject = scanner.nextLine();
            teacher.setName(name);
            teacher.setSubject(subject);
            System.out.println("Teacher updated successfully");
        } else {
            System.out.println("Teacher unfound");
        }
    }

    private void deleteTeacher() {
        System.out.print("Enter the Teacher Id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Teacher teacherToremove = findTeacher(id);
        if (teacherToremove != null) {
            school.deleteTeacher(teacherToremove);
            System.out.println("Teacher deleted successfully");
        } else {
            System.out.println("Teacher not found");
        }
    }

}
