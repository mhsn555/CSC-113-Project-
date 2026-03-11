
public class AdminStaff extends Employee implements Payable {
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
	
	public double calculateMonthlyAmount() {
		return 0;
	} // needs modification
	
	public String getRoleInfo() {
		return "";
	}// needs modification
	
	public String toString() {
		return "name: " + getName() + "  ID: " + getId() + "  age: " + getAge() + "  Department: " + getDepartment() + "  Base Salary: " + getBaseSalary() + "  Position: " + position + "  Allowance: " + allowance;
	} 
	
	
}
