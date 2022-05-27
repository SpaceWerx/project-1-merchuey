package Controller;

import java.util.List;

import com.google.gson.Gson;

import io.javalin.http.Handler;
import Models.Users;
import Service.User_Services;

public class UserController {

	User_Services us = new User_Services();

	public Handler getEmployeesHandler = (ctx) ->{
		
	 List<Users> allUsers = us.getAllUsers();
	
	 Gson gson = new Gson();
	
	 String  JSONObject = gson.toJson(allUsers);
	 
	 ctx.result(JSONObject);
	 ctx.status(200);
		
	};
	
	public Handler insertEmployeesHandler = (ctx) ->{
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		Users employee = gson.fromJson(body, Users.class);
		System.out.println(employee.getUsername());
		us.addUser(employee);
		
		ctx.result("Employee successfully added!");
		ctx.status(201);

	
	};
	
	public Handler GetAllUsers;
	public Handler GetByUsername;
	public Handler GetById;
	
}



