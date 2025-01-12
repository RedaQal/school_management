package view;

import java.util.List;

import model.SchoolClass;
import model.Student;

public class SchoolClassView {
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
        } else {
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
        } else {
            System.out.println("There is no student in this class");
        }
    }
}
