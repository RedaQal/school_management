package controller;

import java.util.Scanner;

import model.School;
import model.SchoolClass;
import model.Student;
import model.Teacher;
import view.SchoolClassView;

public class SchoolClassController {
    private School school;
    private SchoolClassView view;
    private Scanner scanner;

    SchoolClassController() {
        this.school = School.getInstance();
        this.view = new SchoolClassView();
        this.scanner = new Scanner(System.in);
    }

    private Teacher findTeacher(int id) {
        Teacher teacher = school.getTeachers().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        return teacher;

    }

    private Student findStudent(int id) {
        Student student = school.getStudents().stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        return student;
    }

    private SchoolClass findSchoolClasses(int id) {
        SchoolClass schoolClass = school.getClasses().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        return schoolClass;
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
                    view.displaySchoolClasses(school.getClasses());
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Please choose a valid option");
                    break;
            }
        } while (entry != 8);
    }

    private void addSchoolClass() {
        System.out.println("Enter the Class Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (findSchoolClasses(id) != null) {
            System.out.println("A class with this Id already exists");
            return;
        }
        System.out.println("Enter the Class name :");
        String name = scanner.nextLine();
        System.out.println("Enter the Class teacher id :");
        int teacherId = scanner.nextInt();
        scanner.nextLine();
        Teacher teacher = findTeacher(teacherId);
        if (teacher != null) {
            SchoolClass schoolClass = new SchoolClass(id, name, teacher);
            school.addSchoolClass(schoolClass);
            System.out.println("Class added successfully");
        } else {
            System.out.println("There is no theacher with this id");
        }
    }

    private void updateSchoolClass() {
        System.out.println("enter the class id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        SchoolClass schoolClass = findSchoolClasses(id);
        if (schoolClass != null) {
            System.out.println("enter the new class name :");
            String name = scanner.nextLine();
            System.out.println("enter the new teacher id :");
            int teacherId = scanner.nextInt();
            scanner.nextLine();
            Teacher teacher = findTeacher(teacherId);
            schoolClass.setClassName(name);
            schoolClass.setTeacher(teacher);
            System.out.println("The class is updated successfully");
        } else {
            System.out.println("class not found");
        }
    }

    private void deleteSchoolClass() {
        System.out.print("Enter the Class Id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SchoolClass schoolClass = findSchoolClasses(id);
        if (schoolClass != null) {
            school.deleteSchoolClass(schoolClass);
            System.out.println("Class deleted successfully");
        } else {
            System.out.println("Class not found");
        }
    }

    private void addStudentToClass() {
        System.out.println("Enter the class Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the Student Id :");
        int studentId = scanner.nextInt();
        SchoolClass schoolClass = findSchoolClasses(id);
        Student student = findStudent(studentId);
        if (student != null && schoolClass != null) {
            schoolClass.addStudent(student);
            System.out.println("student added to class successfully");
        } else {
            System.out.println("class or student not found");
        }
    }

    private void deleteStudentFromClass() {
        System.out.println("Enter the class Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the Student Id :");
        int studentId = scanner.nextInt();
        SchoolClass schoolClass = findSchoolClasses(id);
        Student student = findStudent(studentId);
        if (student != null && schoolClass != null) {
            schoolClass.removeStudent(student);
            System.out.println("student removed from the class successfully");
        } else {
            System.out.println("class or student are not found");
        }
    }

    private void displayClassStudents() {
        System.out.println("Enter the class Id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        SchoolClass schoolClass = findSchoolClasses(id);
        view.displayClassStudents(schoolClass);
    }

}
