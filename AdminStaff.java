
public class AdminStaff extends Employee {
	private String position;
	private double allowance;
	
	public AdminStaff(String id, String name, int age, String department, double baseSalary,String position ,double allowance) {
		super(id,name,age,department,baseSalary);
		this.position = position;
		this.allowance = allowance;
	}

	public String getPosition() {
		return position;
	}
	
	public double getAllowance() {
		return allowance;
	}
	
	
	public String getRoleInfo() {
    return "Admin Staff - Position: " + position + ", Department: " + getDepartment();
	}
	
	public String toString() {
		return "name: " + getName() + "  ID: " + getId() + "  age: " + getAge() + "  Department: " + getDepartment() + "  Base Salary: " + getBaseSalary() + "  Position: " + position + "  Allowance: " + allowance;
	} 
	
	
}
