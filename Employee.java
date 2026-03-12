
public class Employee extends Person {
	private String department;
	private double baseSalary;
	private double allowance;
	public Employee(String id, String name, int age, String department, double baseSalary, double allowance) {
		super(id,name,age);
		this.department = department;
		this.baseSalary = baseSalary;
		this.allowance = allowance;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public double getBaseSalary() {
		return baseSalary;
	}
	
	public double getAllowance() {
		return allowance;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}

	public String toString() {
		return super.toString() + "  Department: " + department + "  Base Salary: " + baseSalary + "  Allowance: " + allowance;
	}
	
	public double calculateMonthlyAmount() {
		return -(baseSalary + allowance);
	}
	
	public String getRoleInfo() {
    return "Employee - Department: " + department;
	}
	
	

}
