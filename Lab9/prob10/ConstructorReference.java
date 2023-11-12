package prob10;
import java.util.Arrays;

class Human {
	String name;
	int age;
	String gender;

	public Human(String name) {
		this.name = name;
	}

	public Human(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Human(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
}

interface HumanCreator {
	Human createHuman(String name, int age, String gender);
}

public class ConstructorReference {
	public static void main(String args[]) {
		Human[] list = { new Human("Joe", 35, "Male"), new Human("Jane", 45, "Female"), new Human("John", 30, "Male") };

		// Query 1 : Print only Female Candidates names
		System.out.println("Query 1: Female Candidate's Names:");
		Arrays.stream(list).filter(human -> "Female".equals(human.getGender())).map(Human::getName)
				.forEach(System.out::println);

		// Query 2 : Create an object by choosing suitable Interface to the specified
		// constructors(Total 3 constructors)using fourth type of Method Reference
		// ClassName::new. Then print the object status
		System.out.println("\nQuery 2: Object Creation and Status:");
		HumanCreator humanCreator = Human::new;
		Human newHuman = humanCreator.createHuman("NewPerson", 25, "Male");
		System.out.println(
				"Name: " + newHuman.getName() + ", Age: " + newHuman.getAge() + ", Gender: " + newHuman.getGender());

		// Query 3 : Count the male candidates whose age is more than 30
		System.out.println("\nQuery 3: Count of Male Candidates over 30:");
		long maleCandidatesOver30 = Arrays.stream(list)
	                .filter(human -> "Male".equals(human.getGender()) && human.getAge() > 30)
	                .count();
	        System.out.println("Count: " + maleCandidatesOver30);
	}

}
