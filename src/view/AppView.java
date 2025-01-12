package view;

import java.util.List;

import model.SchoolClass;
import model.Student;
import model.Teacher;

public class AppView {
    public void displayMainMenu() {
        System.out.println("------Main Menu ---------");
        System.out.println("1- manage students");
        System.out.println("2- manage teachers");
        System.out.println("3- manage classes");
        System.out.println("4- school details");
        System.out.println("5- quite");
    }

    public void displaySchoolDetails(int studentNb, int teacherMb, int classesNb) {
        System.out.println("|-------School Details-------|");
        System.out.println("Students number : " + studentNb);
        System.out.println("Teacher number : " + teacherMb);
        System.out.println("Classes number : " + classesNb);
    }

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

    public void displayTeacherMenu() {
        System.out.println("|-------Teacher Menu-------|");
        System.out.println("1- add a teacher");
        System.out.println("2- update teacher");
        System.out.println("3- delete teacher");
        System.out.println("4- show teachers");
        System.out.println("5- quite");
    }

    public void displayTeachers(List<Teacher> teachers) {
        if (teachers.isEmpty()) {
            System.out.println("There is no teachers");
        } else {
            System.out.println("-------Teachers List-------");
            for (Teacher s : teachers) {
                System.out.println(s);
            }
        }
    }

    public void displaySchoolClassMenu() {
        System.out.println("|-------Classes Menu-------|");
        System.out.println("1- add a class");
        System.out.println("2- update class");
        System.out.println("3- delete class");
        System.out.println("4- add student to the class");
        System.out.println("5- delete student from a class");
        System.out.println("6- show students");
        System.out.println("7- show classes");
        System.out.println("8- quitte");
    }

    public void displaySchoolClasses(List<SchoolClass> classes) {
        if (!classes.isEmpty()) {
            System.out.println("|-------Class List-------|");
            for (SchoolClass c : classes) {
                System.out.println(c);
            }
        }else{
            System.out.println("There is no class");
        }
    }

    public void displayClassStudents(SchoolClass schoolClass) {
        if (!schoolClass.getStudents().isEmpty()) {
            System.out.println(schoolClass);
            System.out.println("##########Students List##########");
            for (Student s : schoolClass.getStudents()) {
                System.out.println(s);
            }
        }else{
            System.out.println("There is no student in this class");
        }
    }
}
