package school;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {
    private String className;
    private Teacher teacher;
    private List<Student> students;

    public SchoolClass(String className , Teacher teacher ){
        this.className = className;
        this.teacher = teacher;
        this.students = new ArrayList<Student>();
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public List<Student> getStudents() {
        return students;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void addStudent(Student student){
        this.students.add(student);
    }
    public void removeStudent(Student student){
        this.students.remove(student);
    }
    public String toString(){
        return "Class : "+this.getClassName()+" teacher : "+this.getTeacher().getName();
    }
}
