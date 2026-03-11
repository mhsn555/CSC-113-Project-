
public class Employee extends Person {
	private String department;
	private double baseSalary;
	
	public Employee(String id, String name, int age, String department, double baseSalary) {
		super(id,name,age);
		this.department = department;
		this.baseSalary = baseSalary;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public double getBaseSalary() {
		return baseSalary;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public String toString() {
		return "name: " + getName() + "  ID: " + getId() + "  age: " + getAge() + "  Department: " + department + "  Base Salary: " + baseSalary;
	}
	
	public double calculateMonthlyAmount() {
		return 0;
	} // needs modification
	
	public String getRoleInfo() {
    return "Employee - Department: " + department;
	}
	
	

}
