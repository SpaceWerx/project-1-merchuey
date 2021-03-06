package Service;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import DAO.UserDAO;
import Models.Roles;
import Models.Users;


public class User_Services {
	static UserDAO userDAO = new UserDAO();
	
	
	public Users getUsersUsername(String username) {
		return UserDAO.getByUsername(username);
}

///////////////////////////////////////////////////////////////

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
	
	System.out.println("this ID does not exist");
}
////////////////////////////////////////////////////////////////////////

public List<Users>getUserByRole(Roles roles){
	List<Users>byRoles = new ArrayList<>();
	for(Users users : userDAO.getAllUsers()) {
		if(users.getRole()==roles) {
		byRoles.add(users);
	}
}
return byRoles;
}
////////////////////////////////////////////////////////////////////////////

public Users getUserbyId(int Id) {
	return userDAO.getUserbyId(Id);
}
///////////////////////////////////////////////////////////////////////////
public void addUser(Users newEmployee) throws SQLException {
	
	UserDAO.create(newEmployee);
}
public boolean checkUserExistsById(int id) {
	return false;
	
}
}