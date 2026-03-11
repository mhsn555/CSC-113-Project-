
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
		members = new Student [size];
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
		if(memberCount < members.length) {
			members [memberCount++] = s;
		return true;}
		
		else {
			System.out.println("The club is full.");
			return false;
		}
			
	}
	
	public boolean removeMemberById(String id) {
		for(int i = 0 ; i < members.length ; i++) {
			if (members[i] == null)
				continue;
			
			if(id.equals(members[i].getId())) {
				members[i] = null;
				return true;
			}
			else
				continue;
		}
			return false;
	}
	
	public Student searchMemberById(String id) {
		int index = -1;
		for(int i = 0 ; i < members.length ; i++) {
			if(id == members[i].getId()) 
				index = i;
		}
		if(index >= 0)
		return members[index];
		else {
			System.out.println("there is no member with that ID");
			return members[index];
		}
	}
	
	public boolean containsMember() {
		for(int i = 0 ; i < members.length ; i++) {
			if(members[i] != null) 
				return true;	
		}
		return false;
	}// no need for parameter i think
	
	public void displayMembers() {
		for(int i = 0 ; i < members.length ; i++) {
			if(containsMember() == false) {
				System.out.println("there is no members in " + clubName);
				break;	
			}
			
			else if(containsMember() && members[i] != null)
				System.out.println(members[i].toString() + "\n **************************");
			
			}
			}
	
	public String toString() {
		return"club's name: " + clubName + "  club's activity: " + activityType + "  club's supervisor: " + supervisor;
		
	}
	
	
	
	

}
