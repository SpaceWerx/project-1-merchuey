package Service;

import java.util.List;
import java.util.Scanner;
import Models.Status;
import Models.Users;
import Models.Reimbursement;
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

public void displayMenu() {
	
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
			Users employee = userservices.getUserById(userChoice);
			
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
		System.out.println("1.-> view previous requests");
		System.out.println("2. -> submit a reimbursment");
		System.out.println("0. -> return to main menu");
		
		int firstChoice = promptSelection(1,2,0);
		
		switch (firstChoice) {
		case 1: 
			displayPreviousRequests(employee);
			break;
		case 2;
		submitReimbursement(employee):
			break;
		case 0;
		System.out.println("returning to main menu");
		employeePortal = false;
		break;
	default;
	System.out.println("invalid");
	System.out.println();
	System.out.println();
		}
		
	}
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
		case 1;
			displayPendingReimbursements();
			break;
		case 2;
			displayResolvedReimbursments();
			break;
		case 3;
			processReimbursements();
			break;
		case 0;
			System.out.println("Returning to main menu");
			managerPortal = false;
		break;
		
		}

}
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
	case 1;
		reimbursementToBeSubmitted.setType(reimtype.lodging);
		break;
	case 2;
		reimbursementToBeSubmitted.setType(reimtype.travel);
		break;
	case 3;
		reimbursementToBeSubmitted.setType(reimtype.food);
		break;
	case 4;
		reimbursementToBeSubmitted.setType(reimtype.other);
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




	
	
}


}
