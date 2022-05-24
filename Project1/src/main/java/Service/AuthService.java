package Service;

import Models.Users;
import DAO.UserDAO;

public class AuthService {
	public static int register(Users usersToBeRegistered);
	if(UserDAO.getByUsername(userToBeRegistered.getUsername())!=null) {
		throw new NullPoiunterException("username is already taken");
	}
///////////////////////////////////////////////////////////////////////////////////////
	
	public static Users login(String username, String password) {
		Users users;
		
		try {
			users = UserDAO.getByUsername(username);
			
			if(users!=null && password.equals(users.getPassword))) {
				
		System.out.println("succssessful login");
		return users;
			} else if (users!= null && !password.equals(users.getPassword)) {
				
				System.out.println("wrong password");
				return null;
			} else {
		System.out.println("user does not exist");
			}
	
		} catch (Exception e) {
			System.out.println("unsuccessful login");
			e.printStackTrace();
	}
return null 
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
