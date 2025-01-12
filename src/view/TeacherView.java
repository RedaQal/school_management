package view;

import java.util.List;

import model.Teacher;

public class TeacherView {
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
}
