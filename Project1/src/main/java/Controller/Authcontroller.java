package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import Models.Users;
import Service.AuthService;


public class AuthController {

	ObjectMapper Mapper = new ObjectMapper();
    public void handleRegister(Context ctx) {

        try {

            String input = ctx.body();

            Users users = Mapper.readValue(input, Users.class);

            int id = AuthService.register(users);

            if(id == 0) {

                ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
                ctx.result("Registration unsuccessful.");
            }

        } catch (Exception e) {

            ctx.status(HttpCode.INTERNAL_SERVER_ERROR);

            if(!e.getMessage().isEmpty()) {
                ctx.result(e.getMessage());
            }

            e.printStackTrace();
        }

    }
    AuthService as = new AuthService();
    
    public Handler loginHandler = (ctx) -> {
        String body = ctx.body();

        Gson gson = new Gson();
        //I recommend you make this an employee object 
        Users u = gson.fromJson(body, Users.class);

        if(as.login(u.getUsername(), u.getPassword()) == 1) {
            ctx.status(201);
            ctx.result("Manager Login Sucessful!");
        }
        else if(as.login(u.getUsername(), u.getPassword()) == 2) {
            ctx.status(202);
            ctx.result("Employee Login Sucessful!");
        }
        else {
        ctx.result("Login Failed!");
        ctx.status(401);
        }
    };
}
