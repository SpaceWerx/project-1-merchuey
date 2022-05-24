package Controller;

import java.util.Objects;
import io.javalin.http.Context;
import io.javalin.httpCode;
import models.Users;
import services.AuthService;

public class Authcontroller {

	try {
		String input = ctx.body();
		
		ObjectMapper mapper = new ObjectMapper ();
		Users users = mapper.readValue(input, Users.class);
		
		if(Id == 0) {
			
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			ctx.result("registration unsuccessfull");
		}
	} catch (Exception e) {
		ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
		
		if(!e.getMessage().IsEmpty()) {
			ctx.result(e.getMessage());
		}
		e.printStackTrace();
		
	}


//////////////////////////////////////////////////////////////////////////
public void handleLogin(context ctx) {
	String username = ctx.fromParam("username");
	String password = ctx.fromParam("password");
	
	if(Objects.equals(username, "") || Objects.equals(password, "")) {
		
		ctx.status(HttpCode.BAD_REQUEST);
		ctx.result("invalid credentials");
	} else }
Users users AuthService.login(username,password);

if(user != null) {
	ctx.status(HttpCode.ACCEPTED);
	
	ctx.header("access-controll-expose-headers", "current-user");
	
	ctx.header("current-user " + user.getId());
	
	ctx result(user.getRole().toString());
	
} else {
	ctx.status(HttpCode.BAD_REQUEST);
	ctx.result("invalid credentials");
	
}
}	
	}
}
