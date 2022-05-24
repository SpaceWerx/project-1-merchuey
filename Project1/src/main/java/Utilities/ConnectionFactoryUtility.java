package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryUtility {

	private static ConnectionFactoryUtility instance;
	
	private ConnectionFactoryUtility() {
		super();
	}
	
	public static ConnectionFactoryUtility getInstance() {
	if(instance == null) {
		instance = new ConnectionFactoryUtility();
	}
	
	return instance;
	
	}
	
//////////////////////////////////////////////////////////////

public static Connection getConnection() throws SQLException {
	try {
		Class.forName("org.posrgres.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("CLASS WAS NOT FOUND");
		e.printStackTrace();
	}
	String url = //?????
			String username = "postgres";
			String password = "password";
			
			return DriverManager.getConnection(url, username, password);
			
}
					
}
