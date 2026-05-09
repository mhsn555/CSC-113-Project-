import java.io.*;
import java.util.Scanner;


public class School implements Payable {
	private static final String LOGIN_COUNT_FILE = "login.txt";
	private static final String USER_INPUT_FILE = "user_input.txt";
	private String name;
	private CustomLinkedList<Person> people;
	private CustomLinkedList<Classroom> classrooms;
	private CustomLinkedList<Club> clubs;
	
	

	public School(String name, int peopleSize, int classroomSize) {
		this.name = name;
		this.people = new CustomLinkedList<>();
		this.classrooms = new CustomLinkedList<>();
		this.clubs = new CustomLinkedList<>();
	}
	
	

	public boolean addPerson(Person p) {

	    if (searchPersonById(p.getId()) != null) {
	        System.out.println("There is already a person with this ID.");
	        return false;
	    }

	    people.add(p);
	    return true;
	}
	
	public boolean removePersonById(String id) {

	    for (int i = 0; i < people.size(); i++) {
	    	Person p = people.get(i);

	        if (id.equals(p.getId())) {

	            if (p instanceof Student) {

	                for (int j = 0; j < clubs.size(); j++) {
	                	Club c = clubs.get(j);
	                    c.removeMemberById(id);
	                }
	            }

	            if (p instanceof Teacher) {

	                for (int j = 0; j < clubs.size(); j++) {
	                	Club c = clubs.get(j);

	                    Teacher supervisor = c.getSupervisor();

	                    if (supervisor != null &&
	                        id.equals(supervisor.getId())) {

	                        c.setSupervisor(null);
	                    }
	                }
	            }

	            people.remove(p);
	            return true;
	        }
	    }

	    System.out.println("There is no person with that ID.");
	    return false;
	}

	public Person searchPersonById(String id) {
		for (int i = 0 ; i < people.size() ; i++) {
			if (id.equals(people.get(i).getId())) {
				return people.get(i);
			}
		}
		return null;
	}

	public boolean addClassroom(Classroom c) {

	    if (searchClassroomByRoomNumber(c.getRoomNumber()) != null) {
	        System.out.println("There is already a classroom with this room number.");
	        return false;
	    }

	    classrooms.add(c);
	    return true;
	}

	public Classroom searchClassroomByRoomNumber(String roomNumber) {
		for (int i = 0; i < classrooms.size(); i++) {
			Classroom c = classrooms.get(i);

		    if (roomNumber.equals(c.getRoomNumber())) {
		        return c;
		    }
		}
		return null;
	}

	public boolean addClub(Club c) {

	    if (searchClubByName(c.getClubName()) != null) {
	        System.out.println("There is already a club with this name.");
	        return false;
	    }

	    clubs.add(c);
	    return true;
	}

	public boolean removeClubByName(String clubName) {

	    for (int i = 0; i < clubs.size(); i++) {
	    	Club c = clubs.get(i);

	        if (clubName.equals(c.getClubName())) {

	            for (int j = 0; j < people.size(); j++) {
	            	Person p = people.get(j);

	                if (p instanceof Student) {
	                    ((Student) p).leaveClubSilently(clubName);
	                }
	            }

	            clubs.remove(c);
	            return true;
	        }
	    }

	    System.out.println("There is no club with that name.");
	    return false;
	}

	public Club searchClubByName(String clubName) {

	    for (int i = 0; i < clubs.size(); i++) {
	    	Club c = clubs.get(i);

	        if (clubName.equals(c.getClubName())) {
	            return c;
	        }
	    }

	    return null;
	}
	
	public Club searchClubBySupervisorId(String supervisorId) {

	    for (int i = 0; i < clubs.size(); i++) {
	    	Club c = clubs.get(i);

	        Teacher supervisor = c.getSupervisor();

	        if (supervisor != null &&
	            supervisorId.equals(supervisor.getId())) {

	            return c;
	        }
	    }

	    return null;
	}

	public void displayAllClassrooms() {

	    if (classrooms.isEmpty()) {
	        System.out.println("There are no classrooms in the school.");
	        return;
	    }

	    for (int i = 0; i < classrooms.size(); i++) {
	    	Classroom c = classrooms.get(i);
	        System.out.println(c + "\n **************************");
	    }
	}

	public void displayAllClubs() {

	    if (clubs.isEmpty()) {
	        System.out.println("There are no clubs in the school.");
	        return;
	    }

	    for (int i = 0; i < clubs.size(); i++) {
	    	Club c = clubs.get(i);
	        System.out.println(c + "\n **************************");
	    }
	}
	
	public void displayAllPeople() {

	    if (people.isEmpty()) {
	        System.out.println("There are no clubs in the school.");
	        return;
	    }

	    for (int i = 0; i < people.size(); i++) {
	    	Person p = people.get(i);
	        System.out.println(p + "\n **************************");
	    }
	}

	public double calculateMonthlyAmount() {
		double total = 0;

		for (int i = 0; i < people.size(); i++) {
			Person p = people.get(i);
		    total += p.calculateMonthlyAmount();
		}
		
		return total;
	}

	public int countStudentsRecursive() {
		return countStudentsRecursive(0);
	}

	private int countStudentsRecursive(int index) {

	    if (index == people.size()) {
	        return 0;
	    }

	    if (people.get(index) instanceof Student) {
	        return 1 + countStudentsRecursive(index + 1);
	    }

	    return countStudentsRecursive(index + 1);
	}

    // Adds a student to a club and updates both the Student object and the Club object
	public boolean addStudentToClub(String studentId, String clubName) throws ClubFullException{
		Person p = searchPersonById(studentId);
		Club c = searchClubByName(clubName);

		if (p == null) {
			System.out.println("There is no person with that ID.");
			return false;
		}

		if (!(p instanceof Student)) {
			System.out.println("This person is not a student.");
			return false;
		}

		if (c == null) {
			System.out.println("There is no club with that name.");
			return false;
		}

		Student s = (Student) p;

		if (s.hasJoinedClub(clubName)) {
			System.out.println("Student already joined this club.");
			return false;
		}

		boolean addedToStudent = s.joinClub(c);
		if (!addedToStudent) {
			return false;
		}
		

			boolean addedToClub = c.addMember(s);
			if (!addedToClub) {
				s.leaveClubSilently(clubName);
				return false;
			}

			return true;
	}

	// Removes a student from a club and updates both the Student object and the Club object
	public boolean removeStudentFromClub(String studentId, String clubName) throws ClubFullException{
		Person p = searchPersonById(studentId);
		Club c = searchClubByName(clubName);

		if (p == null) {
			System.out.println("There is no person with that ID.");
			return false;
		}

		if (!(p instanceof Student)) {
			System.out.println("This person is not a student.");
			return false;
		}

		if (c == null) {
			System.out.println("There is no club with that name.");
			return false;
		}

		Student s = (Student) p;

		if (!s.hasJoinedClub(clubName)) {
			System.out.println("This student is not a member of that club.");
			return false;
		}

		boolean removedFromClub = c.removeMemberById(studentId);
		if (!removedFromClub) {
			System.out.println("This student is not a member of that club.");
			return false;
		}

		boolean removedFromStudent = s.leaveClub(clubName);
		if (!removedFromStudent) {
			c.addMember(s);
			return false;
		}

		return true;
	}
	
	public double calculateAllSalaries() {

	    double allSalaries = 0;

	    for (int i = 0; i < people.size(); i++) {
	    	Person p = people.get(i);

	        if (p instanceof Employee) {

	            Employee employee = (Employee) p;

	            allSalaries += employee.getBaseSalary()
	                         + employee.getAllowance();
	        }
	    }

	    return allSalaries;
	}
	
	public double calculateAllFees() {

	    double allFees = 0;

	    for (int i = 0; i < people.size(); i++) {
	    	Person p = people.get(i);

	        if (p instanceof Student) {
	            allFees += p.calculateMonthlyAmount();
	        }
	    }

	    return allFees;
	}
	
	
	public String toString() {
		return "School Name: " + name +
			       "  People Count: " + people.size() +
			       "  Classroom Count: " + classrooms.size() +
			       "  Club Count: " + clubs.size();	}
	
	public void saveLoginCount() {
		try (PrintWriter writer = new PrintWriter(LOGIN_COUNT_FILE)) {
			for (int i = 0; i < people.size(); i++) {
				Person p = people.get(i);
				
				if(p instanceof AdminStaff){
					AdminStaff admin = (AdminStaff)p;
					writer.println(admin.getPosition() + " " + admin.getName() + " has logged in: " + admin.getLoginCount() + " times");
				}
				
				else if(p instanceof Teacher) {
					Teacher teacher = (Teacher)p;
					writer.println("Mr." + teacher.getName() + " has logged in: " + teacher.getLoginCount() + " times");
				}
				
				else if(p instanceof Employee) {
					Employee e = (Employee)p;
					writer.println(e.getName() + " has logged in: " + e.getLoginCount() + " times");
				}
				
				
				else if(p instanceof Student) {
					Student student = (Student)p;
					writer.println(student.getName() + " has logged in: " + student.getLoginCount() + " times");
				}
			}
		}
		catch(FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	public void readLoginCount() throws EOFException{
		boolean foundData = false;
		
		try (Scanner read = new Scanner(new File(LOGIN_COUNT_FILE))) {
			while(read.hasNextLine()) {
				foundData = true;
				String r = read.nextLine();
				System.out.println(r);
			}
		}
		catch(FileNotFoundException e1) {
			System.out.println(e1.getMessage());
			return;
		}
		
		if(!foundData) {
			throw new EOFException("No login count data has been saved yet.");
		}
	}
	
	public void saveUserInput(String source, String name, String id) throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(USER_INPUT_FILE, true))) {
			writer.println(source + " | name: " + name + " | id: " + id);
		}
	}
	
	public String readSavedUserInput() {
		StringBuilder savedInput = new StringBuilder();
		
		try (Scanner read = new Scanner(new File(USER_INPUT_FILE))) {
			while(read.hasNextLine()) {
				savedInput.append(read.nextLine()).append(System.lineSeparator());
			}
		}
		catch(FileNotFoundException e1) {
			return "No saved user input file was found.";
		}
		
		if(savedInput.length() == 0) {
			return "No user input has been saved yet.";
		}
		
		return savedInput.toString();
	}
	
}
