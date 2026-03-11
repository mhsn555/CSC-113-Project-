
public class Student extends Person implements Payable {
	private String gradeLevel;
	private double average;
	private double feePerMonth;
	private int clubCount;
	private Club [] clubsJoined;
	
	public Student(String id, String name, int age, String gradeLevel, double average, double feePerMonth, int clubSize) {
		super(id,name,age);
		this.gradeLevel = gradeLevel;
		this.average = average;
		this.feePerMonth = feePerMonth;
		clubsJoined = new Club [clubSize];
	}
	
	public String getGradeLevel() {
		return gradeLevel;
	}
	
	public double getAverage() {
		return average;
	}
	
	public double getFeePerMonth() {
		return feePerMonth;
	}
	
	public boolean joinClub(Club c) {
		if(clubCount < clubsJoined.length) {
			clubsJoined[clubCount++] = c;
			return true;
		}
		else {
			System.out.println("Can't join more Clubs");
			return false;
		}
	}
	
	public boolean leaveClub(String clubName) {
		for(int i = 0 ; i < clubsJoined.length ; i++) {
			if(clubName.equals(clubsJoined[i].getClubName())) {
				clubsJoined[i] = null;
				System.out.println("Student just left " + clubName);
				return true;
			}
		}
		return false;
	}
	
	public Club searchClub(String clubName) {
		int index = -1;
		for(int i = 0 ; i < clubsJoined.length ; i++) {
			if(clubName.equals(clubsJoined[i].getClubName()))
				index = i;
		}
		if(index >= 0)
			return clubsJoined[index];
		else {
			System.out.println("there is Club with that name");
			return clubsJoined[index];}
	}
	
	public boolean hasJoinedClub() {
		for(int i = 0 ; i < clubsJoined.length ; i++) {
			if(clubsJoined[i] != null) 
				return true;
		}
		return false;
	}// no need for parameter i think
	
	public void displayJoinedClubs() {
		for(int i = 0 ; i < clubsJoined.length ; i++) {
		if(hasJoinedClub() == false) {
			System.out.println("Student didn't join any clubs.");
			break;	
		}
		
		else if(hasJoinedClub() && clubsJoined[i] != null)
			System.out.println(clubsJoined[i].toString() + "\n **************************");
		
		}
	}
	
	public double calculateMonthlyAmount() {
		return 0;
	}// needs modification
	
	public String getRoleInfo() {
		return"";
	}// needs modification
	
	public String toString() {
		return "name: " + getName() + "  ID: " + getId() + "  age: " + getAge() + "  Grade Level: " + gradeLevel + "  Average: " + average + "  Fee per month: " + feePerMonth;
	}
	
}
