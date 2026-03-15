import java.util.Scanner;

public class SchoolTest {
	public static void main (String [] args) {

	Scanner input = new Scanner(System.in);
	
	School S = new School("int. School" , 10 , 2 , 2);
	
	Classroom C = new Classroom("10" , "Classroom B" , 2);
	
	Student s1 = new Student("1111", "Ahmed", 14, "8th Grade", 3.4 , 2000, 3);
	Student s2 = new Student("2222", "Saad", 13, "8th Grade", 2.2 , 2000, 3);
	
	Teacher t1 = new Teacher("1234", "Adel" , 46 , "math" , 10000 , 15000 , "math" , 21);
	Teacher t2 = new Teacher("4567", "osama" , 33 , "Science", 6500 , 9900 , "physics" , 12);
	
	Club c1 = new Club("Football" , "Football" , t1 , 2);
	Club c2 = new Club("Science" , "Science experiments" , t2 , 2);
	
	Employee e1 = new Employee("1122", "jehad" , 28 , "finance" , 18000 , 10000);
	Employee e2 = new Employee("2211" , "hazem" , 31 , "HR" , 26000 , 15000);
	
	AdminStaff a = new AdminStaff("9999" , "yusef" , 47 , "Upper management" , 45000, 30000, "General manager" );
	
	S.addClassroom(C);
	S.addPerson(s1);
	S.addPerson(s2);
	S.addPerson(t1);
	S.addPerson(t2);
	S.addClub(c1);
	S.addClub(c2);
	S.addPerson(e1);
	S.addPerson(e2);
	S.addPerson(a);
	
	int choice1;
	int choice2;
	int choice3;
	int choice4;
	int choice5;
	int choice6;
	
	int answer;
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
			do {
				System.out.println("Welcome Student."
						+ "\n do you want to log in (1 for yes , 0 for no): ");
				answer = input.nextInt();
				
				if(answer == 1) {
				System.out.print("Enter your name and Id please."
						+ "\n Name: ");
				String n = input.next();
				System.out.print("Id: ");
				String i = input.next();
				
				if(n.equalsIgnoreCase(s1.getName()) && i.equals(s1.getId())) {
					do {
					System.out.println("Welcome " + s1.getName());
					System.out.print("what would you like to do ?"
							+ "\n 1- show your info"
							+ "\n 2- show clubs you're in"
							+ "\n 3- back to main menu"
							+ "\n choose a number: ");
					choice2 = input.nextInt();
					
					switch(choice2) {
					case(1):
						System.out.println(s1.toString());
						break;
						
					case(2):
						s1.displayJoinedClubs();
						break;
						
					case(3):
						break;
					
					default:
						System.out.println("wrong number");
						break;
					

				}
				
					
				
	
	}while(choice2 != 3);
					
	}
				else if(n.equalsIgnoreCase(s2.getName()) && i.equals(s2.getId())) {

					do {
						System.out.println("Welcome " + s2.getName());
						System.out.print("what would you like to do ?"
								+ "\n 1- show your info"
								+ "\n 2- show clubs you're in"
								+ "\n 3- back to main menu"
								+ "\n choose a number: ");
						choice2 = input.nextInt();
						
						switch(choice2) {
						case(1):
							System.out.println(s2.toString());
							break;
							
						case(2):
							s2.displayJoinedClubs();
							break;
							
						case(3):
							break;
						
						default:
							System.out.println("wrong number");
							break;
						

					}
					
						
					
		
		}while(choice2 != 3);
					
	
				}
				else {
					System.out.println("the name or Id is incorrect");
				}
			}
		
		}while(answer != 0);
		break;
		
		case(2):
			do {
				System.out.print("Welcome Teacher"
					+ "\n do you want to log in (1 for yes , 0 for no): ");
		answer = input.nextInt();
		
		if(answer == 1) {
		System.out.print("Enter your name and Id please."
				+ "\n Name: ");
		String n = input.next();
		System.out.print("Id: ");
		String i = input.next();
		
		if(n.equalsIgnoreCase(t1.getName()) && i.equals(t1.getId())) {
			do {

				System.out.print("Welcome Mr. " + t1.getName()
				+ "\n what would you like to do ?"
				+ "\n 1- show Student in your club and thier info"
				+ "\n 2- show the club your supervising"
				+ "\n 3- add Student to your club"
				+ "\n 4- delete Student from your club"
				+ "\n 5- show your info"
				+ "\n 6- back to main menu"
				+ "\n choose a number: ");
				choice3 = input.nextInt();
				
				switch(choice3) {
				case(1):
					c1.displayMembers();
					break;
				
				case(2):
					System.out.println(c1.toString());
					break;
					
				case(3):
					System.out.print("Enter the Id of the Student you want to add: ");
				String add = input.next();
				S.addStudentToClub(add, c1.getClubName());
				if(c1.searchMemberById(add) != null) 
				System.out.println(c1.searchMemberById(add).getName() + " has been added to the club");
				
					break;
					
				case(4):
					System.out.print("Enter the Id of the Student you want to delete: ");
				String delete = input.next();
				S.removeStudentFromClub(delete, c1.getClubName());
					break;
					
				case(5):
					System.out.println(t1.toString());
					break;
				
				case(6):
					break;
				
				default:
					System.out.println("Wrong number");
					break;
				}
				
				
				
			
			}while(choice3 != 6);
		}
		
		else if(n.equalsIgnoreCase(t2.getName()) && i.equals(t2.getId())) {
			do {

				System.out.print("Welcome Mr. " + t2.getName()
				+ "\n what would you like to do ?"
				+ "\n 1- show Student in your club and thier info"
				+ "\n 2- show the club your supervising"
				+ "\n 3- add Student to your club"
				+ "\n 4- delete Student from your club"
				+ "\n 5- show your info"
				+ "\n 6- back to main menu"
				+ "\n choose a number: ");
				choice3 = input.nextInt();
				
				switch(choice3) {
				case(1):
					c2.displayMembers();
					break;
				
				case(2):
					System.out.println(c2.toString());
					break;
					
				case(3):
					System.out.print("Enter the Id of the Student you want to add: ");
				String add = input.next();
				S.addStudentToClub(add, c2.getClubName());
				if(c2.searchMemberById(add) != null)
				System.out.println(c2.searchMemberById(add).getName() + " has been added to the club");
					break;
					
				case(4):
					System.out.print("Enter the Id of the Student you want to delete: ");
				String delete = input.next();
				S.removeStudentFromClub(delete, c2.getClubName());
					break;
					
				case(5):
					System.out.println(t2.toString());
					break;
				
				default:
					System.out.print("Wrong number");
					break;
				}
				
				
				
			
			}while(choice3 != 6);
		}
		
		else 
			System.out.println("the name or Id is incorrect.");
		
		}
		
		
		}while(answer != 0);
		break;
		
		case(3):
			do {
				System.out.print("Welcome admin."
						+ "\n do want to log in (1 for yes , 0 for no): ");
				answer = input.nextInt();
				
				if(answer == 1) {
						System.out.print("Enter your name and Id please."
							+ "\n Name: ");
					String n = input.next();
					System.out.print("Id: ");
					String i = input.next();
					
					if(n.equalsIgnoreCase(a.getName()) && i.equals(a.getId())) {
						do {
							System.out.print("Welcome Mr. " + a.getName()
						+ "\n what would you like to do ?"
						+ "\n 1- show all the people in the School"
						+ "\n 2- Show all the clubs in the School"
						+ "\n 3- show all classrooms in the School"
						+ "\n 4- remove a club from the School"
						+ "\n 5- back to main menu"
						+ "\n choose a number:");
							choice4 = input.nextInt();
							
						switch(choice4) {
						case(1):
							S.displayAllPeople();
							break;
						
						case(2):
							S.displayAllClubs();
							break;
							
						case(3):
							S.displayAllClassrooms();
							break;
							
						case(4):
							System.out.print("Enter the name of the Club you want remove: ");
						String delete = input.next();
						S.removeClubByName(delete);
						System.out.println(S.searchClubByName(delete) + " club has been removed.");
							break;
							
						case(5):
							break;
						
						default:
							System.out.println("wrong number");
						}
						
						}while(choice4 != 5);
					
						}
					else
						System.out.println("The name or Id is incorrect.");
					
				}
			}while(answer != 0);
			break;
			
		case(4):
			do {
				System.out.print("Welcome Employee."
						+ "\n do want to log in (1 for yes , 0 for no): ");
				answer = input.nextInt();
				
				if(answer == 1) {
					System.out.print("Enter your name and Id please."
							+ "\n Name: ");
					String n = input.next();
					System.out.print("Id: ");
					String i = input.next();
					
					if(n.equalsIgnoreCase(e1.getName()) && i.equals(e1.getId())) {
						do {
							System.out.print("Welcome " + e1.getName()
							+ "\n what would you like to do ?"
							+ "\n 1- Calculate all School staff's salaries"
							+ "\n 2- Calculate all Student's fees per month"
							+ "\n 3- back to main menu"
							+ "\n choose a number: ");
							choice5 = input.nextInt();
							
							switch(choice5){
							case(1):
								System.out.println("all Staff's Salaries equal: " + S.calculateAllSalaries());
								break;
								
							case(2):
								System.out.println("all Student fees equal: " + S.calculateAllFees());
								break;
								
							case(3):
								break;
							
							default:
								System.out.println("Wrong number");
								break;
							}
							
						}while(choice5 != 3);
					}
					
					else if(n.equalsIgnoreCase(e2.getName()) && i.equals(e2.getId())) {
						do {
							System.out.print("Welcome " + e2.getName()
							+ "\n what would you like to do ?"
							+ "\n 1- change an Employee's Base Salary or allownace"
							+ "\n 2- change a Student's fee's"
							+ "\n 3- remove an Employee from the School"
							+ "\n 4- back to main menu"
							+ "\n choose a number: ");
							choice5 = input.nextInt();
							
							switch(choice5) {
							case(1):
								do {System.out.print("which do you want to change ?"
										+ "\n 1- Base Salary"
										+ "\n 2- allowance"
										+ "\n 3- back to your menu"
										+ "\n choose a number: ");
								choice6 =input.nextInt();
								
								switch(choice6) {
								case(1):
									System.out.print("write the Id for the Employee that you want to change their base Salary: ");
								String change1 =input.next();
								if(S.searchPersonById(change1) instanceof Employee) {
									Employee emp = (Employee)S.searchPersonById(change1);
									System.out.print("enter the new Base Salary: ");
									double newS = input.nextDouble();
									emp.setBaseSalary(newS);
								}
								else
									System.out.println("there is no Employee with that Id");
									break;
									
								case(2):
									System.out.print("write the Id for the Employee that you want to change their allowance: ");
								String change2 =input.next();
								
								if(S.searchPersonById(change2) instanceof Employee) {
									Employee emp = (Employee)S.searchPersonById(change2);
									System.out.print("enter the new allowance: ");
									double newA = input.nextDouble();
									emp.setAllowance(newA);
								}
									break;
									
								case(3):
									break;
								}
											}while(choice6 != 3);
								
								break;
								
							case(2):
								System.out.print("Enter the Id of the Student you want to change their fees: ");
							String change = input.next();
							if(S.searchPersonById(change) instanceof Student) {
								Student st = (Student)S.searchPersonById(change);
								System.out.print("enter the new fees: ");
								double newF = input.nextDouble();
								st.setFeePerMonth(newF);

							}
							
							else
								System.out.println("there is no Student with that Id");
								break;
								
							case(3):
								System.out.print("Enter the id of the Employee you want to remove: ");
							String remove = input.next();
							if(S.searchPersonById(remove) instanceof Employee) 
								S.removePersonById(remove);
							else
								System.out.println("there is no Employee with that Id");

								break;
								
							case(4):
								break;
							
							default:
								System.out.println("Wrong number");
								break;
							}
							
						}while(choice5 != 4);
					}
				}
			}while(answer != 0);
			break;
	}
	}while(choice1 > 0 && choice1 <= 4);
	System.out.print("goodbye.");
}
}

