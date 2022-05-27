package com.revature;

import java.sql.Connection;


import java.sql.SQLException;

import Controller.AuthController;
import Controller.ReimbursementController;
import Controller.UserController;
import io.javalin.Javalin;
import Utilities.ConnectionFactoryUtility;
import Service.CLI_Menu;

public class Launcher {
	public static void main(String[] args) {
		AuthController authController = new AuthController();
		UserController userController = new UserController();
		ReimbursementController reimbursementcontroller = new ReimbursementController();
		//CLI_Menu options = new CLI_Menu();
	    //options.displayLoginMenu();
		
		try (Connection conn = ConnectionFactoryUtility.getConnection()) {
			System.out.println("connection successful");
		} catch(SQLException e) {
			System.out.println("connection failed");
			e.printStackTrace();
		}
		
		Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins();
				}
		).start(3000);
		
		app.get("/employee", userController.getEmployeesHandler);
		
		app.post("/employee", userController.insertEmployeesHandler);
		
		app.post("/login", authController.loginHandler);
		
		app.post("/register", authController.handleRegister);
		
		app.get("/status", reimbursementcontroller.handleGetReimbursmentByStatus);
		
		app.get("/reimbursements/{Id}", reimbursementcontroller.handleGetReimbursementById);
		
		app.post("/submit", reimbursementcontroller.handleSubmit);
		
		app.put("/processed", reimbursementcontroller.handleProcessed);
		
		app.get("/author/{author}", reimbursementcontroller.handleGetReimbursementByAuthor);
		
		app.get("reimbursements", reimbursementcontroller.handleGetReimbursements);
		
	}

}
