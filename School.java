import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;


public class School implements Payable {
	private String name;
	private LinkedList<Person> people;
	private LinkedList<Classroom> classrooms;
	private LinkedList<Club> clubs;
	
	

	public School(String name, int peopleSize, int classroomSize) {
		this.name = name;
		this.people = new LinkedList<>();
		this.classrooms = new LinkedList<>();
		this.clubs = new LinkedList<>();
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

	    for (Person p : people) {

	        if (id.equals(p.getId())) {

	            if (p instanceof Student) {

	                for (Club c : clubs) {
	                    c.removeMemberById(id);
	                }
	            }

	            if (p instanceof Teacher) {

	                for (Club c : clubs) {

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
		for (Classroom c : classrooms) {

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

	    for (Club c : clubs) {

	        if (clubName.equals(c.getClubName())) {

	            for (Person p : people) {

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

	    for (Club c : clubs) {

	        if (clubName.equals(c.getClubName())) {
	            return c;
	        }
	    }

	    return null;
	}
	
	public Club searchClubBySupervisorId(String supervisorId) {

	    for (Club c : clubs) {

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

	    for (Classroom c : classrooms) {
	        System.out.println(c + "\n **************************");
	    }
	}

	public void displayAllClubs() {

	    if (clubs.isEmpty()) {
	        System.out.println("There are no clubs in the school.");
	        return;
	    }

	    for (Club c : clubs) {
	        System.out.println(c + "\n **************************");
	    }
	}
	
	public void displayAllPeople() {

	    if (people.isEmpty()) {
	        System.out.println("There are no clubs in the school.");
	        return;
	    }

	    for (Person p : people) {
	        System.out.println(p + "\n **************************");
	    }
	}

	public double calculateMonthlyAmount() {
		double total = 0;

		for (Person p : people) {
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

	    for (Person p : people) {

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

	    for (Person p : people) {

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
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("login.txt");
			for (Person p : people) {
				
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
		finally {
			writer.close();
		}
	}
	
	public void readLoginCount() throws EOFException{
		Scanner read = null;
		try {
			read = new Scanner(new File("login.txt"));
			while(read.hasNextLine()) {
				String r = read.nextLine();
				System.out.println(r);
			}
			if(!read.hasNext())
				throw new EOFException("End of the file");
		
		}
		catch(EOFException e1) {
			System.out.println(e1.getMessage());
		}
		catch(FileNotFoundException e2) {
			System.out.println(e2.getMessage());
		}
		finally {
			read.close();
		}
	}
	
}
