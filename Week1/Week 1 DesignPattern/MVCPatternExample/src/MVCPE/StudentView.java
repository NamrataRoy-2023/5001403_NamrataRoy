package MVCPE;

public class StudentView {
	private Student student;
	
	public StudentView(Student student){
		this.student = student;
	}
	
	public void displayStudentDetails(String name, int id, String grade) {
		System.out.println("Student Name : "+student.name+"\nStudent Id : "+student.id+"\nStudent Grade : "+student.grade);
	}

}
