import java.util.LinkedList;

public class Club {
	private String clubName;
	private String activityType;
	private Teacher supervisor;
	private LinkedList <Student> members;
	private int maxSize;
	
	public Club(String clubName, String activityType, Teacher supervisor, int size) {
		this.clubName = clubName;
		this.activityType = activityType;
		this.supervisor = supervisor;
		this.members = new LinkedList<>();
		maxSize = size;
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
	
	public void setSupervisor(Teacher supervisor) {
		this.supervisor = supervisor;
	}
	
	
	public boolean containsMember(String id) {

	    for (Student s : members) {
	        if (id.equals(s.getId())) {
	            return true;
	        }
	    }

	    return false;
	}
	
	
	public boolean addMember(Student s) throws ClubFullException{
		 if (members.size() >= maxSize) 
		        throw new ClubFullException("The club is full.");

		 else if (containsMember(s.getId())) {
	        System.out.println("This student is already a member.");
	        return false;
	    }
		 else {
	    members.add(s);
	    return true;
	    }
			
	}
	
	public boolean removeMemberById(String id) {

	    for (Student s : members) {
	        if (id.equals(s.getId())) {
	            members.remove(s);
	            return true;
	        }
	    }

	    return false;
	}
	
	public Student searchMemberById(String id) {

	    for (Student s : members) {
	        if (id.equals(s.getId())) {
	            return s;
	        }
	    }

	    System.out.println("There is no member with that ID.");
	    return null;
	}
	
	public void displayMembers() {

	    if (members.isEmpty()) {
	        System.out.println("There are no members in " + clubName);
	        return;
	    }

	    for (Student s : members) {
	        System.out.println(s + "\n **************************");
	    }
	}
	
	public String toString() {
		return "Club name: " + clubName + "  Club's Activity: " + activityType + "  The supervisor for the Club " + supervisor;
	}
	
	
	
	

}
