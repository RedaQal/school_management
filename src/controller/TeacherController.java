package controller;

import java.util.Scanner;

import dao.TeacherDAO;
import model.Teacher;
import view.TeacherView;

public class TeacherController {
    private TeacherDAO teacherDAO;
    private TeacherView view;
    private Scanner scanner;

    public TeacherController() {
        this.teacherDAO = new TeacherDAO();
        this.view = new TeacherView();
        this.scanner = new Scanner(System.in);
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
                    view.displayTeachers(teacherDAO.findAll());
                    break;
                case 5:
                    System.out.println("quite");
                    break;
                default:
                    break;
            }
        } while (entry != 5);
    }

    private void addTeacher() {
        System.out.print("Enter the Teacher name :");
        String name = scanner.nextLine();
        System.out.print("Enter the Teacher subject :");
        String subject = scanner.nextLine();
        Teacher teacher = new Teacher(name, subject);
        teacherDAO.save(teacher);
    }
    private void updateTeacher() {
        System.out.print("enter the Teacher id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        Teacher teacher = teacherDAO.findById(id);
        if (teacher != null) {
            System.out.print("enter the new name :");
            String name = scanner.nextLine();
            System.out.print("enter the new subject :");
            String subject = scanner.nextLine();
            teacher.setName(name);
            teacher.setSubject(subject);
            teacherDAO.update(teacher);
            System.out.println("Teacher updated successfully");
        } else {
            System.out.println("Teacher unfound");
        }
    }
    private void deleteTeacher() {
        System.out.print("Enter the Teacher Id :");
        int id = scanner.nextInt();
        Teacher teacher = teacherDAO.findById(id);
        if (teacher != null) {
            teacherDAO.delete(teacher.getId());
        }else{
            System.out.println("teacher unfound ");
        }
    }
}
