
public class Teacher extends Employee {
	private String subject;
	private int yearsOfExperience;
	
	public Teacher(String id, String name, int age, String department, double baseSalary, double allowance, String subject, int yearsOfExperience ) {
		super(id,name,age,department,baseSalary, allowance);
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
    return "Teacher - Subject: " + subject + ", Years of Experience: " + yearsOfExperience;
	}
	
	public String toString() {
		return super.toString() + "  Subject: " + subject + "  Years of Experience: " + yearsOfExperience;
	}
	

}
