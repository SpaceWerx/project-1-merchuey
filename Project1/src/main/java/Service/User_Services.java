package Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.UserDAO;
import Models.Roles;
import Models.Users;
import java.util.ArrayList;
import java.util.ArrayList;

public class User_Services {
	static UserDAO userDAO = new UserDAO();
	
	
	public Users getUsersUsername(String username);
		return userDAO.getByUsername(username);
}
////////////////////////////////////////////
public List<Users>getAllUsers(){
	return userDAO.getAllUsers();
	}
/////////////////////////////////////////////////////////
public void UserExistsById(int id) {
	for(Users users : userDAO.getAllUsers()) {
		if(users.getId()==id) {
			System.out.println("this ID exists");
			break;
		}
	}
	
	System.out.println("this id does not exist");
}
////////////////////////////////////////////////////////////////////////

public List<Users>getUserByRole(Roles roles){
	List<Users>byRoles = new ArrayList<>();
	for(Users users : UserDAO.getAllUsers()) {
		byRole.add(user);
	}
}
return byRole;
}
////////////////////////////////////////////////////////////////////////////

public Users getUserbyId(int id) {
	return UserDAO.getUserbyId(id);
}
///////////////////////////////////////////////////////////////////////////
public void addUser(Users newEmployee) throws SQLException {
	
	UserDAO.create(newEmployee);
}
public boolean checkUserExistsById(int id) {
	return false;
	
}
}