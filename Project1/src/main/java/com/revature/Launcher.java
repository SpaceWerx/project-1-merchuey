package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import Controller.Authcontroller;
import Controller.ReimbursementController;
import Controller.UserController;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import Models.Users;
import Service.CLI_Menu;
import Utilities.ConnectionFactoryUtility;

public class Launcher {
	public static void main(String[] args) {
		Authcontroller authController = new Authcontroller();
		UserController userController = new UserController();
		ReimbursementController reimbursementcontroller = new ReimbursementController();
		
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
		
		app.get("/employee", UserController.getEmployeesHandler);
		
		app.post("/employee", UserController.insertEmployeesHandler);
	}

}
