
public abstract class Person implements Payable {
	private String id;
	private String name;
	private int age;
	private int loginCount;
	
	public Person(String id ,String name,int age ) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public int getLoginCount() {
		return loginCount;
	}
	
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
	
	public void incrementLoginCount() {
	    loginCount++;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	public abstract double calculateMonthlyAmount();
	
	public abstract String getRoleInfo();
	
	public String toString() {
		return "name: " + name + "  ID: " + id + "  age: " + age;
		
	}
	
	

}
