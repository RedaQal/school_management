package controller;

import java.util.Scanner;
import view.AppView;

public class AppController {
    private AppView view;
    private Scanner scanner;
    private SchoolController schoolController;
    private StudentController studentController;
    private TeacherController teacherController;
    private SchoolClassController schoolClassController;

    public AppController() {
        this.view = new AppView();
        this.scanner = new Scanner(System.in);
        schoolController = new SchoolController();
        studentController = new StudentController();
        teacherController = new TeacherController();
        schoolClassController = new SchoolClassController();
    }

    public void start() {
        int entry;
        do {
            view.displayMainMenu();
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry) {
                case 1:
                    studentController.manageStudent();
                    break;
                case 2:
                    teacherController.manageTeacher();
                    break;
                case 3:
                    schoolClassController.manageSchoolClass();
                    break;
                case 4:
                    schoolController.schoolDetails();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please choose a valid option");
                    break;
            }
        } while (entry != 5);
    }
}
