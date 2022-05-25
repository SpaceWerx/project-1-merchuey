package Service;

import java.util.List;
import java.util.Scanner;
import Models.Status;
import Models.Users;
import Models.Reimbursement;
import Models.Reimtype;
import Models.Roles;

public class CLI_Menu {
	Reimservices rService = new Reimservices();
	User_Services userservices = new User_Services ();
	
	static Scanner scan = new Scanner(System.in);
	public static String fetchInput() {
		return scan.nextLine().split(".")[0];		
	}
	
	public static int promptSelection(int ...validEntries) {
		int input;
		boolean valid = false;
		
		do {
			input = parseIntegerInput(fetchInput());
			for(int entry : validEntries) {
				if(entry == input) {
					valid = true;
					break;	
				}
			}
			
			if(!valid) {
				System.out.println("input recieved was not valid. try again");
			}
		} while(!valid);
		return input;
	}
/////////////////////////////////////////////////////////////////////////////////////////
	public static int parseIntegerInput(String input) {
 try {
	 return Integer.parseInt(input);
    }catch (NumberFormatException e) {
    	System.out.println("input was not correctly entered. try again please");
    	return - 1;
    }
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
public static double parseDoubleInput(String input) {
	try {
		return Double.parseDouble(input);
	} catch (NumberFormatException e) {
		System.out.println("invalid input. please try again");
		return -1;
		
	}
}

public void DisplayMenu() {
	
	boolean menuOptions = true;
	
	System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
	System.out.println("Welcome to Revature Reimbursement Services");
	System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
	System.out.println();
	
	while(menuOptions) {
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		System.out.println("select reimbursement type below");
		System.out.println("1.employee portal");
		System.out.println("2.finanice manager");
		System.out.println("0.exit application");
		System.out.println();

		int firstChoice = promptSelection(1,2,0);
		
		switch(firstChoice) {
		
		case 1: 
			handlePortal(Roles.Employee);
			break;
			
		case 2:
		handlePortal(Roles.Manager);
		break;
		
		case 0:
		System.out.println("have a nice day!");
		menuOptions = false;
		break;
		}
	}
}
		
public void handlePortal(Roles role) {
		List<Users> users = userservices.getUserByRole(role);
		
		int[] ids = new int [users.size() + 1];
		ids[users.size()] = 0;
		
		for (int i = 0; i < users.size(); i++) 
	    {
	        ids[i] = users.get(i).getId();
	    }

		
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		System.out.println("enter number please");
		
		for (Users u: users) {
			System.out.println(u.getId() +  "-> " + u.getUsername()); }
			System.out.println("0 -> return to main menu");
			System.out.println();
			
			int userChoice = promptSelection(ids);
			
			if (userChoice == 0) {
				return;
			}
			Users employee = userservices.getUserbyId(userChoice);
			
			if (role == Roles.Manager) {
				System.out.println("opening manager portal for " + employee.getUsername());
				displayFinanceManagerMenu(employee);
			} else {
				System.out.println("opening employee portal for " + employee.getUsername());
				displayEmployeeMenu(employee);
			}
			
		}

public void displayEmployeeMenu(Users employee) {
	
	boolean employeePortal = true;
	
	System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
	System.out.println("welcome to the employee portal");
	System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
	System.out.println();
	
	while (employeePortal) {
		
		System.out.println("please enter your choise");
		System.out.println("1 -> view previous requests");
		System.out.println("2 -> submit a reimbursment");
		System.out.println("0 -> return to main menu");
		
		int firstChoice = promptSelection(1,2,0);
		
		switch (firstChoice) {
		case 1: 
			displayPreviousRequests(employee);
			break;
		case 2:
		submitReimbursement(employee);
			break;
		case 0:
		System.out.println("returning to main menu");
		employeePortal = false;
		break;
	default:
	System.out.println("invalid");
	System.out.println();
	System.out.println();
		}
		
	}
}
private void displayPreviousRequests(Users employee) {
	// TODO Auto-generated method stub
	
}

//////////////////////////////////////////////////////////////////////////////////////
public void displayFinanceManagerMenu(Users manager) {
	boolean managerPortal = true;
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("welcome to the manager portal " + manager.getUsername());
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println();

	while(managerPortal) {
		System.out.println("please enter the any number");
		System.out.println("1 -> view all pending reimbursements");
		System.out.println("2-> view all resolved reimbursemnts");
		System.out.println("3 -> process a reimbursment");
		System.out.println("0 -> return to main menu");
		
		int firstChoice = promptSelection(1,2,3,0);
		
		switch (firstChoice) {
		case 1:
			displayPendingReimbursements();
			break;
		case 2:
			displayResolvedReimbursments();
			break;
		case 3:
			processReimbursements();
			break;
		case 0:
			System.out.println("Returning to main menu");
			managerPortal = false;
		break;
		
		}
	}
}
	

private void processReimbursements() {
	// TODO Auto-generated method stub
	
}

private void displayResolvedReimbursments() {
	// TODO Auto-generated method stub
	
}

/////////////////////////////////////////////////////////////////////////////////////
public void submitReimbursement(Users employee) {
	Reimbursement reimbursementToBeSubmitted = new Reimbursement();
	reimbursementToBeSubmitted.setAuthor(employee.getId());
	
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("what type of reimbursement would you like to submit?");
	System.out.println("1-> lodging");
	System.out.println("2-> travel");
	System.out.println("3-> food");
	System.out.println("4-> other");
	int typeDecision = promptSelection(1,2,3,4);
	
	switch (typeDecision) {
	case 1:
		reimbursementToBeSubmitted.setType(Reimtype.Lodging);
		break;
	case 2:
		reimbursementToBeSubmitted.setType(Reimtype.Travel);
		break;
	case 3:
		reimbursementToBeSubmitted.setType(Reimtype.Food);
		break;
	case 4:
		reimbursementToBeSubmitted.setType(Reimtype.Other);
		break;
	}
	
	System.out.println("please enter reimbursement amount");
	System.out.println("$");
	
	reimbursementToBeSubmitted.setAmount(parseDoubleInput(fetchInput()));
	if(reimbursementToBeSubmitted.getAmount() <= 0 ) {
		System.out.println("amount invalid. please enter correct dollar amount");
		boolean valid = false;
		while (!valid) {
			reimbursementToBeSubmitted.setAmount(parseDoubleInput(fetchInput()));
			if (reimbursementToBeSubmitted.getAmount() !=0) {
				valid = true;
	}
}
}

System.out.println("please enter a reason for your reimbursement request");

reimbursementToBeSubmitted.setDescription(scan.nextLine());
if (reimbursementToBeSubmitted.getDescription().trim().equals("")) {
	System.out.println("you can not request an empty description");
	boolean valid = false;
	
	while (!valid) {
		reimbursementToBeSubmitted.setDescription(scan.nextLine());
		if(!reimbursementToBeSubmitted.getDescription().trim().equals("")) {
			valid = true;
		}
	}
}
rService.submitReimbursement(reimbursementToBeSubmitted);

}

//////////////////////////////////////////////////////////////////////////////////////////////////////

public void displayPendingReimbursements() {
	List<Reimbursement> pendingReimbursements = rService.getPendingReimbursements();
	
	if(pendingReimbursements.isEmpty()) {
		System.out.println("no pending requests");
		System.out.println("returing to previous menu");
	}
	
	for (Reimbursement r : resolvedReimbursements) {
		System.out.println(r);
	}
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void processReimbursement(Users manager) {
	boolean processPortal = true;
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("welcome to the processing portal");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println();
	
	while (proecssPortal) {
		List<Reimbursement> reimbursements = rService.getPendingReimbursements();
		
		if(reimbursements.isEmpty()) {
			System.out.println("there are no reimbursements to process");
			System.out.println("returing to previous menu");
			return;	
		}
	}
}
		
		int[] ids = new int[Reimbursement.size()]; {
		for (int i = 0;i<Reimbursement.size(); i++) {
			Reimbursement r = Reimbursement.get(i);
			Users author = UserService.getUserById(r.getAuthor());
			System.out.println(r.getID() + "->" + author.getUsername() + " $ " + r.getAmount());
			ids[i] = r.getID();	
		}
		}
	System.out.println("please enter the ID of the reimbursement you would like to process");
	
	int selection = promptSelection(ids);
	Reimbursement reimbursementToBeProcessed = rService.getReimbursementById(selection);
	System.out.println("proessing reimbursement" + reimbursementToBeProcessed.getId());
	System.out.println("Details\nAuthor: " + reimbursementToBeProcessed.getAuthor()).getUsername() + "\nAmount: "
	+ reimbursementToBeProcessed.getAmount() + "n\Description: " + reimbursementToBeProcessed.getDescription());
	
	System.out.println("enter number of choice please");
	System.out.println("1 -> approve");
	System.out.println("2 -> deny");
	
	int decision = promptSelection(1,2);
	Status status = (decision == 1)? Status.Approved : Status.Denied;
	rService.update(reimbursement.ToBeProcessed,manager.getID(). status);
	
	System.out.println("would you like to process another reimbursement");
	System.out.println("1 -> yes");
	System.out.println("2 -> no");
	
	
	int lastChoice = promptSelection(1,2);
	if (lastChoice == 2) {
		processPortal = false;
	}
	
	

/////////////////////////////////////////////////////////////////////////////////////////////////

public void displayLoginMenu() {
	AuthService au = new AuthService();
	boolean accountFound = false;
	
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("welcome to the log in portal");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println();
	
	while(accountFound!= true) {
		System.out.println("enter username");
		 String username = scan.nextLine();
		System.out.println("enter password");
		String password = scan.nextLine();
		if(au.login(username, password)! = null) {
			accountFound = true;
			displayMenu();
			break;
		} else {
			System.out.println("account not found. please try again");
		}	
	}	
}
///////////////////////////////////////////////////////////////////////////////////////////////////

public void displayMenu() {
	AuthService au = new AuthService();
	
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("welcome to the registration portal");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println();
	
	Users usersToBeRegistered = new Users();
	System.out.println("enter username please");
	String username = scan.nextLine();
	System.out.println("enter password please");
	String password = scan.nextLine();
	userToBeRegistered.setUsername(username);
	userToBeRegistered.setPassword(password);
	userToBeRegistered.setRoles(Roles.Employee);
	au.register(userToBeRegistered);
	displayLoginMenu
}
}
 
