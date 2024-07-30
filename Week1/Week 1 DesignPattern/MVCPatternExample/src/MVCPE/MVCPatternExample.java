package MVCPE;
import java.util.Scanner;

public class MVCPatternExample {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the name of the Student : ");
		String student_name = scanner.nextLine();

		System.out.println("Enter the id of the Student : ");
		int student_id = scanner.nextInt();
		
		System.out.println("Enter the grade of the Student : ");
		String student_grade = scanner.next();
		
		
		Student s = new Student(student_name, student_id, student_grade);
		StudentView sv = new StudentView(s);
		StudentController sc = new StudentController(s, sv);
		
		System.out.println("\nDetails of the Student : \n");
		sv.displayStudentDetails(student_name, student_id, student_grade);
		
		System.out.println("\nDetails of the updated Student : \n");
		
		System.out.println("\nEnter the name of the Student : ");
		String updated_student_name = scanner.nextLine();
		sc.update_StudentName(updated_student_name);
		
		System.out.println("\nEnter the id of the Student : ");
		int updated_student_id = scanner.nextInt();
		sc.update_StudentId(updated_student_id);
		
		System.out.println("\nEnter the grade of the Student : ");
		String updated_student_grade = scanner.next();
		sc.update_StudentGrade(updated_student_grade);
		
//		sv.displayStudentDetails(student_name, student_id, student_grade);
		
		scanner.close();
	}

}
