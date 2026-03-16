public class School implements Payable {
	private String name;
	private int peopleCount;
	private int classroomCount;
	private int clubCount;

	private Person[] people;
	private Classroom[] classrooms;
	private Club[] clubs;

	public School(String name, int peopleSize, int classroomSize, int clubSize) {
		this.name = name;
		this.people = new Person[peopleSize];
		this.classrooms = new Classroom[classroomSize];
		this.clubs = new Club[clubSize];
		this.peopleCount = 0;
		this.classroomCount = 0;
		this.clubCount = 0;
	}

	public boolean addPerson(Person p) {
		if (peopleCount >= people.length) {
			System.out.println("Cannot add more people.");
			return false;
		}

		if (searchPersonById(p.getId()) != null) {
			System.out.println("There is already a person with this ID.");
			return false;
		}

		people[peopleCount++] = p;
		return true;
	}

	public boolean removePersonById(String id) {
		for (int i = 0; i < peopleCount; i++) {
			if (id.equals(people[i].getId())) {
				if (people[i] instanceof Student) {
					for (int j = 0; j < clubCount; j++) {
						clubs[j].removeMemberById(id);
					}
				}
				
				if (people[i] instanceof Teacher) {
					for (int j = 0; j < clubCount; j++) {
						Teacher supervisor = clubs[j].getSupervisor();
						if (supervisor != null && id.equals(supervisor.getId())) {
							clubs[j].setSupervisor(null);
						}
					}
				}

				for (int j = i; j < peopleCount - 1; j++) {
					people[j] = people[j + 1];
				}

				people[--peopleCount] = null;
				return true;
			}
		}

		System.out.println("There is no person with that ID.");
		return false;
	}

	public Person searchPersonById(String id) {
		for (int i = 0; i < peopleCount; i++) {
			if (id.equals(people[i].getId())) {
				return people[i];
			}
		}
		return null;
	}

	public boolean addClassroom(Classroom c) {
		if (classroomCount >= classrooms.length) {
			System.out.println("Cannot add more classrooms.");
			return false;
		}

		if (searchClassroomByRoomNumber(c.getRoomNumber()) != null) {
			System.out.println("There is already a classroom with this room number.");
			return false;
		}

		classrooms[classroomCount++] = c;
		return true;
	}

	public Classroom searchClassroomByRoomNumber(String roomNumber) {
		for (int i = 0; i < classroomCount; i++) {
			if (roomNumber.equals(classrooms[i].getRoomNumber())) {
				return classrooms[i];
			}
		}
		return null;
	}

	public boolean addClub(Club c) {
		if (clubCount >= clubs.length) {
			System.out.println("Cannot add more clubs.");
			return false;
		}

		if (searchClubByName(c.getClubName()) != null) {
			System.out.println("There is already a club with this name.");
			return false;
		}

		clubs[clubCount++] = c;
		return true;
	}

	public boolean removeClubByName(String clubName) {
		for (int i = 0; i < clubCount; i++) {
			if (clubName.equals(clubs[i].getClubName())) {
				for (int j = 0; j < peopleCount; j++) {
					if (people[j] instanceof Student) {
						((Student) people[j]).leaveClubSilently(clubName);
					}
				}
				
				for (int j = i; j < clubCount - 1; j++) {
					clubs[j] = clubs[j + 1];
				}

				clubs[--clubCount] = null;
				return true;
			}
		}

		System.out.println("There is no club with that name.");
		return false;
	}

	public Club searchClubByName(String clubName) {
		for (int i = 0; i < clubCount; i++) {
			if (clubName.equals(clubs[i].getClubName())) {
				return clubs[i];
			}
		}
		return null;
	}
	
	public Club searchClubBySupervisorId(String supervisorId) {
		for (int i = 0; i < clubCount; i++) {
			Teacher supervisor = clubs[i].getSupervisor();
			if (supervisor != null && supervisorId.equals(supervisor.getId())) {
				return clubs[i];
			}
		}
		return null;
	}

	public void displayAllPeople() {
		if (peopleCount == 0) {
			System.out.println("There are no people in the school.");
			return;
		}

		for (int i = 0; i < peopleCount; i++) {
			System.out.println(people[i].toString());
			System.out.println("Role Info: " + people[i].getRoleInfo());
			System.out.println("**************************");
		}
	}

	public void displayAllClassrooms() {
		if (classroomCount == 0) {
			System.out.println("There are no classrooms in the school.");
			return;
		}

		for (int i = 0; i < classroomCount; i++) {
			System.out.println(classrooms[i].toString() + "\n **************************");
		}
	}

	public void displayAllClubs() {
		if (clubCount == 0) {
			System.out.println("There are no clubs in the school.");
			return;
		}

		for (int i = 0; i < clubCount; i++) {
			System.out.println(clubs[i].toString() + "\n **************************");
		}
	}

	public double calculateMonthlyAmount() {
		double total = 0;

		for (int i = 0; i < peopleCount; i++) {
			total += people[i].calculateMonthlyAmount();
		}

		return total;
	}

	public int countStudentsRecursive() {
		return countStudentsRecursive(0);
	}

	private int countStudentsRecursive(int index) {
		if (index == peopleCount) {
			return 0;
		}

		if (people[index] instanceof Student) {
			return 1 + countStudentsRecursive(index + 1);
		}

		return countStudentsRecursive(index + 1);
	}

    // Adds a student to a club and updates both the Student object and the Club object
	public boolean addStudentToClub(String studentId, String clubName) {
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
	public boolean removeStudentFromClub(String studentId, String clubName) {
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
		for(int i = 0 ; i < peopleCount ; i++) {
			if(people[i] instanceof Employee) {
				Employee employee = (Employee) people[i];
				allSalaries += employee.getBaseSalary() + employee.getAllowance();
			}
		}
		return allSalaries;
	}
	
	public double calculateAllFees() {
		double allFees = 0;
		for(int i = 0 ; i < peopleCount ; i++) {
			if(people[i] instanceof Student) {
				allFees += people[i].calculateMonthlyAmount();
			}
			
		}
		return allFees;
	}
	
	
	public String toString() {
		return "School Name: " + name + "  People Count: " + peopleCount + "  Classroom Count: " + classroomCount+ "  Club Count: " + clubCount;
	}
}
