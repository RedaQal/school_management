package controller;

import model.School;
import view.SchoolView;

public class SchoolController {
   private School school;
   private SchoolView view;

   public SchoolController(School school) {
      this.school = school;
      this.view = new SchoolView();
   }

   public void schoolDetails() {
      int studentNb = school.getStudents().size();
      int teacherMb = school.getTeachers().size();
      int classesNb = school.getClasses().size();
      view.displaySchoolDetails(studentNb,teacherMb,classesNb);
   }
}
