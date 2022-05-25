package Models;

public class Users {
	private int Id;
	private String user;
	private String password;
	private Roles roles;
	
	public Users() {
		super();
	}
	
	public Users(int id, String user, String password, Roles role ) {
		super();
		this.Id = id;
		this.user = user;
		this.password = password;
		this.roles = role;
	}
	public Users( String user, String password, Roles role) {
		super();
		this.user = user;
		this.password = password;
		this.roles = role;
	}
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		this.Id = id;
	}
	public Roles getRole() {
		
		return roles;
	}
	public String getUsername() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	public void setRole(Roles role) {
		this.roles = role;
	}
	public void setPassword(String password) {
		this.password = password;
		
	}
	public void setUsername(String username) {
		this.user = username;
		
	}


}


/*package Models;

public class Users {
	private int id;
	private String username;
	private String password; 
	private Roles role;
	public Users(String f_name, String l_name, int roleId) {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	

}
*/