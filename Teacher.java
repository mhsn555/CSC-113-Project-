
public class Teacher extends Employee {
	private String subject;
	private int yearsOfExperience;
	
	public Teacher(String id, String name, int age, String department, double baseSalary, String subject, int yearsOfExperience ) {
		super(id,name,age,department,baseSalary);
		this.subject = subject;
		this.yearsOfExperience = yearsOfExperience;
		
	}
	
	public String getSubject() {
		return subject;
	}
	
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
	
	public String getRoleInfo() {
		return"";
	}// needs modification
	
	public String toString() {
		return "name: " + getName() + "  ID: " + getId() + "  age: " + getAge() + "  Department: " + getDepartment() + "  Base Salary: " + getBaseSalary() + "  Subject: " + subject + "  Years of Experience: " + yearsOfExperience;
	}
	
	Teacher [] supervisor;

}
