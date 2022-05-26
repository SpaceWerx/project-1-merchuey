package Controller;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import Models.Users;
import Service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Authcontroller {}
/*ObjectMapper Mapper = new ObjectMapper();
public void handlerRegister(Context ctx) {

	try {
		String input = ctx.body();
		
		Users users = Mapper.readValue(input, Users.class);
		int Id = AuthService.register(users);
		if(Id == 0) {
			
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			ctx.result("registration unsuccessfull");
		}
	} catch (Exception e) {
		ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
		
		if(!e.getMessage().isEmpty()) {
			ctx.result(e.getMessage());
		}
		e.printStackTrace(); 
		
	}
} */






 



//////////////////////////////////////////////////////////////////////////

public void handleLogin(context.ctx) {
	String username = ctx.fromParam("username");
	String password = ctx.fromParam("password");
	
	if(Objects.equals(username, "") || Objects.equals(password, "")); {
		
		ctx.status(HttpCode.BAD_REQUEST);
		ctx.result("invalid credentials");
	} else {

Users users; AuthService.login(username,password); {

if(user != null) {
	ctx.status(HttpCode.ACCEPTED);
	
	ctx.header("access-controll-expose-headers", "current-user");
	
	ctx.header("current-user " + user.getId());
	
	ctx.result(User.getRole().toString());

	} else {
	ctx.status(HttpCode.BAD_REQUEST);
	ctx.result("invalid credentials");
	}
}
	}
}


	



	


