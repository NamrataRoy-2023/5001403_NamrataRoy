package MVCPE;

public class StudentController {
	private Student student;
	private StudentView view;

	public StudentController(Student student, StudentView view) {
		this.student = student;
		this.view = view;
	}
	
	public void update_StudentName(String newName) {
		student.name = newName;
		view.displayStudentDetails(newName,student.id, student.grade);
	}
	
	public void update_StudentId(int newId) {
		student.id = newId;
		view.displayStudentDetails(student.name, newId, student.grade);
	}
	
	public void update_StudentGrade(String newGrade) {
		student.grade = newGrade;
		view.displayStudentDetails(student.name,student.id, newGrade);
	}
	
}
