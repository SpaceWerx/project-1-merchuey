package Models;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Service.Reimservices;
import Service.User_Servies;

public class Menu {
		
		User_Servies es = new User_Servies(); //why do i have so many imports? is that okay?
		Reimservices rs = new Reimservices();
		
		public void displayMenu() throws SQLException {
			
			boolean displayMenu = true; 
			Scanner scan = new Scanner(System.in);
			//what is the meaning of scan is never closed?
			//give the user a pretty greeting :)
			System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
			System.out.println("Welcome to Robin Hood Reimbursement Services");
			System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
			
			while(displayMenu) { 
				
				//menu options
				System.out.println("hi! -> get greeted");
				System.out.println("users -> show all users");
				System.out.println("usersById -> get users with a certain ID");
				System.out.println("usersByTitle -> get userss of a certain title");
				System.out.println("add -> add a new users");
				System.out.println("exit -> exit the application");
				
				String input = scan.nextLine();
				
				
				switch(input) {
				
				case "hi": {
					System.out.println("Hello there.");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");

					break; //we need a break in each case block, or else all the other cases will still run
				}
				
				case "users" :{
					
					//get the List of employees from the repository layer
					List<Users> users = es.users();
					
					//enhanced for loop to print out the Employees one by one
					for (Users e : users) {
						System.out.println(e);
					}
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
					break;
				}
				
				case "userById" :{
					System.out.println("Who you looking for?");
					
					int idInput = scan.nextInt(); //get user's input for id
					scan.nextLine(); //we still need nextLine so that we can move to the next line for more input
					
					//what if the user inputs a String? program crashes
					//up to you to polish your project a bit and add some foolproofing mechanisms
					
					List<Users> users = es.getUsersById(idInput);
					
					for(Users emp : users) {
						System.out.println(emp);
					}
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
					break;
				}
				
				case "userbytitle": {
					
					System.out.println("Enter User Role to Search: (Careful, this is case sensitive");
					String roleInput = scan.nextLine(); //get user's input for Role to search by
					
					List<Users> users = es.getUsersByRoleTitle(roleInput); //get the List of Employees from the dao
					
					for(Users e : users)
					{
						System.out.println(e); //print them out one by one via the enhanced for loop
					}
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
					break;				
				}
				
				case "add" : {
					
					//we need to prompt the user for the employee's name, and their role_id
					System.out.println("Enter User First Name");
					String f_name = scan.nextLine();
					
					System.out.println("Enter User Last Name");
					String l_name = scan.nextLine();
					
					System.out.println("Enter Role Id: 1) Manager 2) Accountatnt 3)  4) Marketing 5) Director");
					int roleId = scan.nextInt(); 
					scan.nextLine(); 
					
					Users newUsers = new Users (f_name, l_name, roleId);
					
					es.addUsers(newUsers);
					
					break;
				}
					
					case "updateSalary": {
					
					System.out.println("Enter Role Title to change");
					String titleInput = scan.nextLine();
					
					System.out.println("Enter a new Salary for this Role");
					int salaryInput = scan.nextInt();
					scan.nextLine();
					
					rs.updateSalary(titleInput, salaryInput);
					break;
				}
				
				case "exit": {
					displayMenu = false;
					break;
				}
				
				//this default block will catch any user inputs that don't match a valid menu option
				default: {
					System.out.println("Invalid selection... try again :'( ");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
					System.out.println(" ");
					break;
				}
				
				
				} //end of switch
				
			} //end of while loop
			
		}	
	}


