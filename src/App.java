import school.School;
import school.SchoolClass;
import school.Student;
import school.Teacher;

public class App {
    public static void main(String[] args) throws Exception {
        // Créer une nouvelle instance de la classe School (École)
        School school = new School();

        // Créer de nouveaux étudiants
        Student student1 = new Student("Reda QALQOL", 21);
        Student student2 = new Student("Abdel KOBBI", 25);

        // Ajouter les étudiants à l'école
        school.addStudent(student1);
        school.addStudent(student2);

        // Créer de nouveaux enseignants
        Teacher teacher = new Teacher("Mohammed QALQOL", "Informatique");

        // Ajouter les enseignants à l'école
        school.addTeacher(teacher);

        // Créer de nouvelles classes avec un nom et un enseignant
        SchoolClass classeMath = new SchoolClass("algo", teacher);

        classeMath.addStudent(student1);
        classeMath.addStudent(student2);

        school.addSchoolClass(classeMath);

        System.out.println("School info : ");
        System.out.println("Total number of students : " + school.getStudents().size());
        System.out.println("Total number of teachers : " + school.getTeachers().size());
        System.out.println("Total number of classes : " + school.getClasses().size());
        System.out.println();

        System.out.println("Class algo info :");
        System.out.println("Class name : " + classeMath.getClassName());
        System.out.println("Teacher : " + classeMath.getTeacher().getName());
        System.out.println("Students number : " + classeMath.getStudents().size());
        System.out.println();

        school.deleteStudent(student1);
        school.deleteSchoolClass(classeMath);

        System.out.println("School Info after deleting a student and a class :");
        System.out.println("Total number of students : " + school.getStudents().size());
        System.out.println("Total number of teachers : " + school.getTeachers().size());
        System.out.println("Total number of classes : " + school.getClasses().size());
        System.out.println();
        Student student3 = new Student("Sophie Martin", 20);
        Student student4 = new Student("Jean Dupont", 21);
        school.addStudent(student3);
        school.addStudent(student4);

        Teacher teacherEnglish = new Teacher("Barry Dukili", "Anglais");
        Teacher teacherScience = new Teacher("Marie Curie", "Science");
        school.addTeacher(teacherEnglish);
        school.addTeacher(teacherScience);

        SchoolClass classeEnglish = new SchoolClass("Anglais", teacherEnglish);
        classeEnglish.addStudent(student3);
        classeEnglish.addStudent(student4);
        school.addSchoolClass(classeEnglish);

        SchoolClass classeScience = new SchoolClass("Science", teacherScience);
        classeScience.addStudent(student1);
        school.addSchoolClass(classeScience);
        System.out.println("School Info after adding students and two class :");
        System.out.println("Total number of students : " + school.getStudents().size());
        System.out.println("Total number of teachers : " + school.getTeachers().size());
        System.out.println("Total number of classes : " + school.getClasses().size());

        System.out.println("\nInformation about the English class:");
        System.out.println("Teacher: " + classeEnglish.getTeacher().getName());
        System.out.println("Number of students : " + classeEnglish.getStudents().size());

        System.out.println("\nInformation about the Science class:");
        System.out.println("Teacher: " + classeScience.getTeacher().getName());
        System.out.println("Number of students : " + classeScience.getStudents().size());
    }
}