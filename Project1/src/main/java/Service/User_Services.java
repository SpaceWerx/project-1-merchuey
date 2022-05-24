package Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.UserDAO;
import Models.Roles;
import Models.Users;
public class User_Services {
static UserDAO userDAO = new UserDAO();



public static Users getUserByUsername(String username) {
    return userDAO.getByUsername(username);
}
//////////////////////////////////////////////////

public List<Users> getAllUsers() {
    return userDAO.getAllUsers();
}
/////////////////////////////////////////////////////////////////////////////////////////////////
public void UserExistsById(int id) {
for(Users users : userDAO.getAllUsers()) {
    if(users.getId()== id) {
        System.out.println("This ID exists");
        break;
    }
}
    System.out.println("This ID does not exist");
}
//////////////////////////////////////////////
public List<Users> getUserByRole(Roles role){
    List<Users> byRole = new ArrayList<>();
    for(Users users : userDAO.getAllUsers()) {
        if(users.getRole() == role) 
        {
            byRole.add(users);
        }
    }

    return byRole;
}
/////////////////////////////////////////////////////////////////////////
public static Users getUserById(int id) {
    return userDAO.getUserbyId(id);
}
//////////////////////////////////////////////////////////////////////////
public void addUser(Users newEmployee) throws SQLException {


    userDAO.create(newEmployee);
}

public boolean checkUserExistsById(int id) {
    return false;
}
}
