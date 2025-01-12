package controller;

import dao.SchoolClassDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import view.SchoolView;

public class SchoolController {
   private SchoolClassDAO schoolClassDAO;
   private TeacherDAO teacherDAO;
   private StudentDAO studentDAO;
   private SchoolView view;

   public SchoolController() {
      this.studentDAO = new StudentDAO();
      this.teacherDAO = new TeacherDAO();
      this.schoolClassDAO = new SchoolClassDAO();
      this.view = new SchoolView();
   }

   public void schoolDetails() {
      int studentNb = studentDAO.findAll().size();
      int teacherMb = teacherDAO.findAll().size();
      int classesNb = schoolClassDAO.findAll().size();
      view.displaySchoolDetails(studentNb,teacherMb,classesNb);
   }
}
