package controller;

import java.util.Scanner;

import dao.SchoolClassDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import model.SchoolClass;
import model.Student;
import model.Teacher;
import view.SchoolClassView;

public class SchoolClassController {
    private TeacherDAO teacherDAO;
    private SchoolClassDAO schoolClassDAO;
    private StudentDAO studentDAO;
    private SchoolClassView view;
    private Scanner scanner;

    SchoolClassController() {
        this.view = new SchoolClassView();
        this.scanner = new Scanner(System.in);
        this.schoolClassDAO = new SchoolClassDAO();
        this.teacherDAO = new TeacherDAO();
        this.studentDAO = new StudentDAO();
    }

    public void manageSchoolClass() {
        int entry;
        do {
            view.displaySchoolClassMenu();
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry) {
                case 1:
                    addSchoolClass();
                    break;
                case 2:
                    updateSchoolClass();
                    break;
                case 3:
                    deleteSchoolClass();
                    break;
                case 4:
                    addStudentToClass();
                    break;
                case 5:
                    deleteStudentFromClass();
                    break;
                case 6:
                    displayClassStudents();
                    break;
                case 7:
                    view.displaySchoolClasses(schoolClassDAO.findAll());
                    break;
                case 8:
                    System.out.println("quite");
                    break;
                default:
                    break;
            }
        } while (entry != 8);
    }

    private void addSchoolClass() {
        System.out.print("Enter the Class name :");
        String name = scanner.nextLine();
        System.out.print("Enter the Class teacher id :");
        int teacherId = scanner.nextInt();
        scanner.nextLine();
        Teacher teacher = teacherDAO.findById(teacherId);
        if (teacher != null) {
            SchoolClass schoolClass = new SchoolClass(name, teacher);
            schoolClassDAO.save(schoolClass);
            System.out.println("class added successfully");
        }else{
            System.out.println("teacher not found");
        }
    }

    private void updateSchoolClass() {
        System.out.print("enter the class id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        SchoolClass schoolClass = schoolClassDAO.findById(id);
        if (schoolClass != null) {
            System.out.print("enter the new class name :");
            String name = scanner.nextLine();
            System.out.print("enter the new teacher id :");
            int teacherId = scanner.nextInt();
            scanner.nextLine();
            Teacher teacher = teacherDAO.findById(teacherId);
            schoolClass.setClassName(name);
            schoolClass.setTeacher(teacher);
            schoolClassDAO.update(schoolClass);
            System.out.println("The class is updated successfully");
        } else {
            System.out.println("class not found");
        }
    }

    private void deleteSchoolClass() {
        System.out.print("Enter the class Id :");
        int id = scanner.nextInt();
        SchoolClass schoolClass = schoolClassDAO.findById(id);
        if (schoolClass != null) {
            schoolClassDAO.delete(schoolClass.getId());
            System.out.println("class is deleted successfully");
        }else{
            System.out.println("class not found");
        }
    }
    private void addStudentToClass() {
        System.out.print("Enter the class Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the Student Id :");
        int studentId = scanner.nextInt();
        SchoolClass schoolClass = schoolClassDAO.findById(id);
        Student student = studentDAO.findById(studentId);
        if (student != null && schoolClass != null) {
            schoolClassDAO.addStudentToClass(student, schoolClass);
            System.out.println("student added to class successfully");
        } else {
            System.out.println("class or student not found");
        }
    }

    private void deleteStudentFromClass() {
        System.out.print("Enter the class Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the Student Id :");
        int studentId = scanner.nextInt();
        SchoolClass schoolClass = schoolClassDAO.findById(id);
        Student student = studentDAO.findById(studentId);
        if (student != null && schoolClass != null) {
            schoolClassDAO.deleteStudentFromClass(student, schoolClass);
            System.out.println("student removed from the class successfully");
        } else {
            System.out.println("class or student are not found");
        }
    }

    private void displayClassStudents() {
        System.out.print("Enter the class Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        SchoolClass schoolClass = schoolClassDAO.findById(id);
        schoolClass = schoolClassDAO.findStudentsOfClass(schoolClass);
        view.displayClassStudents(schoolClass);
    }
}
