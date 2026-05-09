public class Student extends Person {
	private String gradeLevel;
	private double average;
	private double feePerMonth;
	private CustomLinkedList<Club> clubsJoined;
	
	public Student(String id, String name, int age, String gradeLevel, double average, double feePerMonth, int clubSize) {
		super(id,name,age);
		this.gradeLevel = gradeLevel;
		this.average = average;
		this.feePerMonth = feePerMonth;
		clubsJoined = new CustomLinkedList<>();
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
	
	public void setFeePerMonth(double feePerMonth) {
		this.feePerMonth = feePerMonth;
	}
	
	public boolean joinClub(Club c) {

	    if (hasJoinedClub(c.getClubName())) {
	        System.out.println("Student already joined this club.");
	        return false;
	    }

	    clubsJoined.add(c);
	    return true;
	}
	
	public boolean leaveClub(String clubName) {
		return leaveClubInternal(clubName, true, true);
	}
	
	public boolean leaveClubSilently(String clubName) {
		return leaveClubInternal(clubName, false, false);
	}
	
	private boolean leaveClubInternal(String clubName, boolean printSuccess, boolean printFailure) {

	    for (int i = 0; i < clubsJoined.size(); i++) {
	    	Club c = clubsJoined.get(i);

	        if (clubName.equals(c.getClubName())) {

	            clubsJoined.remove(c);

	            if (printSuccess) {
	                System.out.println("Student just left " + clubName);
	            }

	            return true;
	        }
	    }

	    if (printFailure) {
	        System.out.println("Student did not join a club with that name.");
	    }

	    return false;
	}
	
	public Club searchClub(String clubName) {

	    for (int i = 0; i < clubsJoined.size(); i++) {
	    	Club c = clubsJoined.get(i);

	        if (clubName.equals(c.getClubName())) {
	            return c;
	        }
	    }

	    System.out.println("There is no club with that name.");
	    return null;
	}
	
	
	public boolean hasJoinedClub(String clubName) {

	    for (int i = 0; i < clubsJoined.size(); i++) {
	    	Club c = clubsJoined.get(i);

	        if (clubName.equals(c.getClubName())) {
	            return true;
	        }
	    }

	    return false;
	}
	
	
	public void displayJoinedClubs() {

	    if (clubsJoined.isEmpty()) {
	        System.out.println("Student didn't join any clubs.");
	        return;
	    }

	    for (int i = 0; i < clubsJoined.size(); i++) {
	    	Club c = clubsJoined.get(i);
	        System.out.println(c + "\n **************************");
	    }
	}
	
	public double calculateMonthlyAmount() {
		return feePerMonth;
	}
	
	public String getRoleInfo() {
    return "Student - Grade Level: " + gradeLevel + ", Average: " + average;
	}
	
	public String toString() {
		return super.toString() + "  Grade Level: " + gradeLevel + "  Average: " + average + "  Fee per month: " + feePerMonth;
	}
	
}
