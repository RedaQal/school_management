package controller;

import java.util.Scanner;

import model.School;
import model.SchoolClass;
import model.Student;
import model.Teacher;
import view.AppView;

public class ApppController {
    private final Scanner scanner;
    private final School school;
    private final AppView view;

    public ApppController(School school, AppView appView) {
        this.school = school;
        this.scanner = new Scanner(System.in);
        this.view = appView;
    }

    public void start() {
        int entry;
        do {
            view.displayMainMenu();
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry) {
                case 1:
                    manageStudent();
                    break;
                case 2:
                    manageTeacher();
                    break;
                case 3:
                    manageSchoolClass();
                    break;
                case 4:
                    schoolDetails();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please choose a valid option");
                    break;
            }
        } while (entry != 5);
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

    // School details
    public void schoolDetails() {
        int studentNb = school.getStudents().size();
        int teacherMb = school.getTeachers().size();
        int classesNb = school.getClasses().size();
        view.displaySchoolDetails(studentNb, teacherMb, classesNb);
    }

    // Students management
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

    // tTeacher management
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

    // schoolClass management
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
