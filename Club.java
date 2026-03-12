
public class Club {
	private String clubName;
	private String activityType;
	private int memberCount;
	private Teacher supervisor;
	private Student [] members;
	
	public Club(String clubName, String activityType, Teacher supervisor, int size) {
		this.clubName = clubName;
		this.activityType = activityType;
		this.supervisor = supervisor;
		this.members = new Student [size];
		this.memberCount = 0;
	}
	
	public String getClubName() {
		return clubName;
	} 
	
	public String getActivityType() {
		return activityType;
	}
	
	
	public Teacher getSupervisor() {
		return supervisor;
	}
	
	public boolean addMember(Student s) {
		if(memberCount >= members.length) {
			System.out.println("The club is full.");
			return false;
			}
		else if (containsMember(s.getId())){
			System.out.println("This student is already a member.");
			return false;
		}
		else {
			members [memberCount++] = s;
			return true;
		}
			
	}
	
	public boolean removeMemberById(String id) {
		for(int i = 0 ; i < memberCount ; i++) {
			if(id.equals(members[i].getId())) {
				for (int j = i; j < memberCount - 1; j++) {
					members[j] = members[j + 1];
				}
				members[--memberCount] = null;
				return true;
			}
		}
			return false;
	}
	
	public Student searchMemberById(String id) {
		for(int i = 0 ; i < memberCount ; i++) {
			if (id.equals(members[i].getId())) {
				return members[i];
			}
		}
		System.out.println("There is no member with that ID.");
		return null;
	}
	
	public boolean containsMember(String id) {
		for(int i = 0 ; i < memberCount ; i++) {
			if(id.equals(members[i].getId())) 
				return true;	
		}
		return false;
	}
	
	public void displayMembers() {
		if (memberCount == 0) {
			System.out.println("There are no members in " + clubName);
			return;
		}
		for (int i = 0; i < memberCount; i++) {
			System.out.println(members[i].toString() + "\n **************************");
		}
			}
	
	public String toString() {
		return"club's name: " + clubName + "  club's activity: " + activityType + "  club's supervisor: " + supervisor;
		
	}
	
	
	
	

}
