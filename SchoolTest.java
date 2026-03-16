import java.util.Scanner;

public class SchoolTest {
	public static void main(String [] args) {
		Scanner input = new Scanner(System.in);
		
		School school = new School("int. School" , 10 , 2 , 2);
		
		Classroom classroom = new Classroom("10" , "Classroom B" , 2);
		
		Student s1 = new Student("1111", "Ahmed", 14, "8th Grade", 3.4 , 2000, 3);
		Student s2 = new Student("2222", "Saad", 13, "8th Grade", 2.2 , 2000, 3);
		
		Teacher t1 = new Teacher("1234", "Adel" , 46 , "math" , 10000 , 15000 , "math" , 21);
		Teacher t2 = new Teacher("4567", "osama" , 33 , "Science", 6500 , 9900 , "physics" , 12);
		
		Club c1 = new Club("Football" , "Football" , t1 , 2);
		Club c2 = new Club("Science" , "Science experiments" , t2 , 2);
		
		Employee e1 = new Employee("1122", "jehad" , 28 , "finance" , 18000 , 10000);
		Employee e2 = new Employee("2211" , "hazem" , 31 , "HR" , 26000 , 15000);
		
		AdminStaff admin = new AdminStaff("9999" , "yusef" , 47 , "Upper management" , 45000, 30000, "General manager" );
		
		school.addClassroom(classroom);
		school.addPerson(s1);
		school.addPerson(s2);
		school.addPerson(t1);
		school.addPerson(t2);
		school.addClub(c1);
		school.addClub(c2);
		school.addPerson(e1);
		school.addPerson(e2);
		school.addPerson(admin);
		
		int choice1;
		do {
			System.out.print("Welcome to the School System."
					+ "\n 1- Student log in"
					+ "\n 2- Teacher log in"
					+ "\n 3- Admin log in"
					+ "\n 4- Empoloyee log in"
					+ "\n 5- to get out"
					+ "\n choose a number to log in: ");
			choice1 = input.nextInt();
			
			switch(choice1) {
			case(1):
				handleStudentLogin(input, school);
				break;
			
			case(2):
				handleTeacherLogin(input, school);
				break;
			
			case(3):
				handleAdminLogin(input, school);
				break;
			
			case(4):
				handleEmployeeLogin(input, school, e1.getId(), e2.getId());
				break;
			
			case(5):
				break;
			
			default:
				System.out.println("wrong number");
				break;
			}
		} while(choice1 != 5);
		
		System.out.print("goodbye.");
	}
	
	private static void handleStudentLogin(Scanner input, School school) {
		int answer;
		do {
			System.out.println("Welcome Student." + "\n do you want to log in (1 for yes , 0 for no): ");
			answer = input.nextInt();

			if (answer == 1) {
				Person person = authenticate(input, school);

				if (person instanceof Student) {
					Student student = (Student) person;
					showStudentMenu(input, student);
				} else {
					System.out.println("the name or Id is incorrect");
				}
			}
		} while (answer != 0);
	}

	private static void handleTeacherLogin(Scanner input, School school) {
		int answer;
		do {
			System.out.print("Welcome Teacher" + "\n do you want to log in (1 for yes , 0 for no): ");
			answer = input.nextInt();

			if (answer == 1) {
				Person person = authenticate(input, school);

				if (person instanceof Teacher) {
					Teacher teacher = (Teacher) person;
					showTeacherMenu(input, school, teacher);
				} else {
					System.out.println("the name or Id is incorrect.");
				}
			}
		} while (answer != 0);
	}

	private static void handleAdminLogin(Scanner input, School school) {
		int answer;
		do {
			System.out.print("Welcome admin." + "\n do want to log in (1 for yes , 0 for no): ");
			answer = input.nextInt();

			if (answer == 1) {
				Person person = authenticate(input, school);

				if (person instanceof AdminStaff) {
					AdminStaff admin = (AdminStaff) person;
					showAdminMenu(input, school, admin);
				} else {
					System.out.println("The name or Id is incorrect.");
				}
			}
		} while (answer != 0);
	}
	
	private static void handleEmployeeLogin(Scanner input, School school, String financeEmployeeId, String hrEmployeeId) {
		int answer;
		do {
			System.out.print("Welcome Employee."
					+ "\n do want to log in (1 for yes , 0 for no): ");
			answer = input.nextInt();
			
			if(answer == 1) {
				Employee employee = authenticateEmployee(input, school);
				if(employee == null) {
					System.out.println("the name or Id is incorrect.");
				}
				else if(financeEmployeeId.equals(employee.getId())) {
					showFinanceEmployeeMenu(input, school, employee);
				}
				else if(hrEmployeeId.equals(employee.getId())) {
					showHrEmployeeMenu(input, school, employee);
				}
				else {
					System.out.println("This employee does not have an assigned menu.");
				}
			}
		} while(answer != 0);
	}
	
	private static void showStudentMenu(Scanner input, Student student) {
		int choice;
		do {
			System.out.println("Welcome " + student.getName());
			System.out.print("what would you like to do ?"
					+ "\n 1- show your info"
					+ "\n 2- show clubs you're in"
					+ "\n 3- back to main menu"
					+ "\n choose a number: ");
			choice = input.nextInt();
			
			switch(choice) {
			case(1):
				System.out.println(student.toString());
				break;
			
			case(2):
				student.displayJoinedClubs();
				break;
			
			case(3):
				break;
			
			default:
				System.out.println("wrong number");
				break;
			}
		} while(choice != 3);
	}
	
	private static void showTeacherMenu(Scanner input, School school, Teacher teacher) {
		int choice;
		do {
			System.out.print("Welcome Mr. " + teacher.getName()
					+ "\n what would you like to do ?"
					+ "\n 1- show Student in your club and thier info"
					+ "\n 2- show the club your supervising"
					+ "\n 3- add Student to your club"
					+ "\n 4- delete Student from your club"
					+ "\n 5- show your info"
					+ "\n 6- back to main menu"
					+ "\n choose a number: ");
			choice = input.nextInt();
			
			Club supervisedClub = school.searchClubBySupervisorId(teacher.getId());
			
			switch(choice) {
			case(1):
				if(supervisedClub == null) {
					System.out.println("You are not supervising an active club.");
				}
				else {
					supervisedClub.displayMembers();
				}
				break;
			
			case(2):
				if(supervisedClub == null) {
					System.out.println("You are not supervising an active club.");
				}
				else {
					System.out.println(supervisedClub.toString());
				}
				break;
			
			case(3):
				if(supervisedClub == null) {
					System.out.println("You are not supervising an active club.");
					break;
				}
				
				System.out.print("Enter the Id of the Student you want to add: ");
				String add = input.next();
				if(school.addStudentToClub(add, supervisedClub.getClubName())) {
					Person addedStudent = school.searchPersonById(add);
					if(addedStudent != null) {
						System.out.println(addedStudent.getName() + " has been added to the club");
					}
				}
				break;
			
			case(4):
				if(supervisedClub == null) {
					System.out.println("You are not supervising an active club.");
					break;
				}
				
				System.out.print("Enter the Id of the Student you want to delete: ");
				String delete = input.next();
				Person removedStudent = school.searchPersonById(delete);
				if(school.removeStudentFromClub(delete, supervisedClub.getClubName()) && removedStudent != null) {
					System.out.println(removedStudent.getName() + " has been removed from the club");
				}
				break;
			
			case(5):
				System.out.println(teacher.toString());
				break;
			
			case(6):
				break;
			
			default:
				System.out.println("Wrong number");
				break;
			}
		} while(choice != 6);
	}
	
	private static void showAdminMenu(Scanner input, School school, AdminStaff admin) {
		int choice;
		do {
			System.out.print("Welcome Mr. " + admin.getName()
					+ "\n what would you like to do ?"
					+ "\n 1- show all the people in the School"
					+ "\n 2- Show all the clubs in the School"
					+ "\n 3- show all classrooms in the School"
					+ "\n 4- remove a club from the School"
					+ "\n 5- back to main menu"
					+ "\n choose a number:");
			choice = input.nextInt();
			
			switch(choice) {
			case(1):
				school.displayAllPeople();
				break;
			
			case(2):
				school.displayAllClubs();
				break;
			
			case(3):
				school.displayAllClassrooms();
				break;
			
			case(4):
				System.out.print("Enter the name of the Club you want remove: ");
				String delete = input.next();
				if(school.removeClubByName(delete)) {
					System.out.println(delete + " club has been removed.");
				}
				break;
			
			case(5):
				break;
			
			default:
				System.out.println("wrong number");
			}
		} while(choice != 5);
	}
	
	private static void showFinanceEmployeeMenu(Scanner input, School school, Employee employee) {
		int choice;
		do {
			System.out.print("Welcome " + employee.getName()
					+ "\n what would you like to do ?"
					+ "\n 1- Calculate all School staff's salaries"
					+ "\n 2- Calculate all Student's fees per month"
					+ "\n 3- back to main menu"
					+ "\n choose a number: ");
			choice = input.nextInt();
			
			switch(choice){
			case(1):
				System.out.println("all Staff's Salaries equal: " + school.calculateAllSalaries());
				break;
				
			case(2):
				System.out.println("all Student fees equal: " + school.calculateAllFees());
				break;
				
			case(3):
				break;
			
			default:
				System.out.println("Wrong number");
				break;
			}
		} while(choice != 3);
	}
	
	private static void showHrEmployeeMenu(Scanner input, School school, Employee employee) {
		int choice;
		do {
			System.out.print("Welcome " + employee.getName()
					+ "\n what would you like to do ?"
					+ "\n 1- change an Employee's Base Salary or allownace"
					+ "\n 2- change a Student's fee's"
					+ "\n 3- remove an Employee from the School"
					+ "\n 4- back to main menu"
					+ "\n choose a number: ");
			choice = input.nextInt();
			
			switch(choice) {
			case(1):
				showSalaryChangeMenu(input, school);
				break;
			
			case(2):
				System.out.print("Enter the Id of the Student you want to change their fees: ");
				String studentId = input.next();
				if(school.searchPersonById(studentId) instanceof Student) {
					Student student = (Student) school.searchPersonById(studentId);
					System.out.print("enter the new fees: ");
					double newFees = input.nextDouble();
					student.setFeePerMonth(newFees);
				}
				else {
					System.out.println("there is no Student with that Id");
				}
				break;
			
			case(3):
				System.out.print("Enter the id of the Employee you want to remove: ");
				String remove = input.next();
				if(school.searchPersonById(remove) instanceof Employee) {
					if(school.removePersonById(remove) && remove.equals(employee.getId())) {
						System.out.println("You have been removed from the school.");
						return;
					}
				}
				else {
					System.out.println("there is no Employee with that Id");
				}
				break;
			
			case(4):
				break;
			
			default:
				System.out.println("Wrong number");
				break;
			}
		} while(choice != 4);
	}
	
	private static void showSalaryChangeMenu(Scanner input, School school) {
		int choice;
		do {
			System.out.print("which do you want to change ?"
					+ "\n 1- Base Salary"
					+ "\n 2- allowance"
					+ "\n 3- back to your menu"
					+ "\n choose a number: ");
			choice = input.nextInt();
			
			switch(choice) {
			case(1):
				System.out.print("write the Id for the Employee that you want to change their base Salary: ");
				String employeeIdForSalary = input.next();
				if(school.searchPersonById(employeeIdForSalary) instanceof Employee) {
					Employee emp = (Employee) school.searchPersonById(employeeIdForSalary);
					System.out.print("enter the new Base Salary: ");
					double newSalary = input.nextDouble();
					emp.setBaseSalary(newSalary);
				}
				else {
					System.out.println("there is no Employee with that Id");
				}
				break;
				
			case(2):
				System.out.print("write the Id for the Employee that you want to change their allowance: ");
				String employeeIdForAllowance = input.next();
				if(school.searchPersonById(employeeIdForAllowance) instanceof Employee) {
					Employee emp = (Employee) school.searchPersonById(employeeIdForAllowance);
					System.out.print("enter the new allowance: ");
					double newAllowance = input.nextDouble();
					emp.setAllowance(newAllowance);
				}
				else {
					System.out.println("there is no Employee with that Id");
				}
				break;
				
			case(3):
				break;
			
			default:
				System.out.println("Wrong number");
				break;
			}
		} while(choice != 3);
	}
	
	private static Person authenticate(Scanner input, School school) {
		return readPersonByCredentials(input, school);
	}
	
	private static Employee authenticateEmployee(Scanner input, School school) {
		Person person = readPersonByCredentials(input, school);
		if(person instanceof Employee && !(person instanceof AdminStaff)) {
			return (Employee) person;
		}
		return null;
	}
	
	private static Person readPersonByCredentials(Scanner input, School school) {
		System.out.print("Enter your name and Id please."
				+ "\n Name: ");
		String name = input.next();
		System.out.print("Id: ");
		String id = input.next();
		
		Person person = school.searchPersonById(id);
		if(person != null && person.getName().equalsIgnoreCase(name)) {
			return person;
		}
		return null;
	}
}
