
public class AdminStaff extends Employee {
	private String position;
	
	public AdminStaff(String id, String name, int age, String department, double baseSalary,double allowance, String position) {
		super(id,name,age,department,baseSalary, allowance);
		this.position = position;
	}

	public String getPosition() {
		return position;
	}
	
	public String getRoleInfo() {
    return "Admin Staff - Position: " + position + ", Department: " + getDepartment();
	}
	
	public String toString() {
		return super.toString() + "  Position: " + position;
	} 
	
	
}
