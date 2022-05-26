package Service;

import Models.Users;
import DAO.UserDAO;

public class AuthService {
	public static int register (Users usersToBeRegistered) {
	if(UserDAO.getByUsername(usersToBeRegistered.getUsername())!=null) {
		throw new NullPointerException("username is already taken"); }
	return UserDAO.create(usersToBeRegistered); 
	}
///////////////////////////////////////////////////////////////////////////////////////
	
	public static int login(String username, String password) {
		
		
		try {
			Users users = UserDAO.getByUsername(username);
			
			if(users!=null && password.equals(users.getPassword())) {
				
		System.out.println("succssessful login");
		return 1;
			} else if (users!= null && !password.equals(users.getPassword())) {
				
				System.out.println("wrong password");
				return 2;
			} else {
		System.out.println("user does not exist");
		
			}
	
		} catch (Exception e) {
			System.out.println("unsuccessful login");
			e.printStackTrace();
	}
return 0; 
	}
		


////////////////////////////////////////////////////////////////////////////

public static Object login(int anyInt) {
	return null;
}
//////////////////////////////////////////////////////////////
public static Object update(String string) {
	return null;
	
}

}
