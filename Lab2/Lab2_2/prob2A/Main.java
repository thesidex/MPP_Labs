package prob2A;

public class Main {

	public static void main(String[] args) {
		Student stu = new Student("Charles");
		stu.getGradeReport().setGrade("A");
		System.out.println(String.format("%s score is %s", stu.getName(), stu.getGradeReport()) );
	}
}
