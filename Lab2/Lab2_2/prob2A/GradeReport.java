package prob2A;

public class GradeReport {
	
	private String grade;
	private Student mystudent;
	
	GradeReport(Student student) {
		this.mystudent = student;
	}

	public Student getMystudent() {
		return mystudent;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return grade;
	}
}
