package Controller;


import Models.Status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import DAO.ReimbursementDAO;
import Models.Reimbursement;
import Service.Reimservices;
import Service.User_Services;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

public class ReimbursementController {
	ReimbursementDAO rDAO = new ReimbursementDAO();
	ObjectMapper objectMapper = new ObjectMapper();
	Reimservices reimbursementService = new Reimservices();
	User_Services user_Service = new User_Services();
///////////////////////////////////////////////////////////////////////////////////////////
	
	public Handler handleSubmit = (ctx) ->{

        String body = ctx.body();
        Gson gson = new Gson();

        Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);

        Reimservices.update(reimbursement);
        String JSONObject = gson.toJson("Reimbursement processed successfully!");
        ctx.result(JSONObject);
        ctx.status(208);

        int id = Reimservices.submitReimbursement(reimbursement);


        if(id !=0) {
            ctx.status(HttpCode.CREATED);
            ctx.result("" + id);
        } else {
            ctx.status(HttpCode.BAD_REQUEST);
            ctx.result("Reimbursement submission was unsuccessful");
        }

};
/////////////////////////////////////////////////////////////////////////////
	public void handleProcess(Context ctx) {
		
		String authHeader = ctx.header("Current-User");
		
		if(authHeader != null) {
			
			int userId = Integer.parseInt(authHeader);
			
		try {
			String reimbursementIdInput = ctx.pathParam("id");
			
			int id = Integer.parseInt(reimbursementIdInput);
			
			String statusInput = ctx.formParam("status");
			
			Reimbursement reimbursement = reimbursementService.getReimbursementById(id);
			
			if(reimbursement != null) {
				
				Reimbursement processedReimbursement = reimbursementService.update(reimbursement);
				
				ctx.status(HttpCode.ACCEPTED);
				ctx.json(processedReimbursement);
			
			} else {
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Reimbursement processing was not successful");
			}
		} catch (Exception e) {
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			
			if(!e.getMessage().isEmpty()) {
				ctx.result(e.getMessage());
	
	} else {
		ctx.status(HttpCode.FORBIDDEN);
		ctx.result("Missing Current User Header with ID"); }
			
		} }	
	
		}
//////////////////////////////////////////////////////////////////////////////////////////////////		
public Handler handleGetReimbursements = (ctx) -> {
//	/if(ctx.queryParam("author") != null) {
//		handleGetReimbursmentByStatus(ctx);
//	} else if(ctx.queryParam("status") != null) {
//		handleGetReimbursmentByStatus(ctx);
	List<Reimbursement> allReim = rDAO.getAllReimbursements();
	
	Gson gson = new Gson();
	String JSONObject = gson.toJson(allReim);
	
	ctx.result(JSONObject);
	ctx.status(200);

//	} 
};
//////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////

public Handler handleGetReimbursementById = (ctx) -> {

	int id = Integer.parseInt(ctx.pathParam("author"));
    System.out.println(id);

    List<Reimbursement> reimId = Reimservices.getReimbursementByAuthor(id);

    Gson gson = new Gson();
    String JSONObject = gson.toJson(reimId);

    ctx.status(HttpCode.OK);
    ctx.result(JSONObject);
} ;
//////////////////////////////////////////////////////////////////////////////////////////////

public Handler handleGetReimbursementByAuthor = (ctx) -> {
	int id = Integer.parseInt(ctx.pathParam("author"));
    System.out.println(id);

    List<Reimbursement> reimId = Reimservices.getReimbursementByAuthor(id);

    Gson gson = new Gson();
    String JSONObject = gson.toJson(reimId);

    ctx.status(HttpCode.OK);
    ctx.result(JSONObject);
};

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public Handler handleProcessed = (ctx) -> {
	String body = ctx.body();
	Gson gson = new Gson();
	Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
	int id = reimbursement.getResolver();
	
	Reimbursement processedReimbursement = Reimservices.update(reimbursement);
	if(processedReimbursement != null) {
		ctx.status(HttpCode.ACCEPTED);
		
	}else {
		ctx.status(HttpCode.ACCEPTED);
		ctx.result("reimbursement processing was not successfull");
	
	}
};
public Handler handleGetReimbursmentByStatus=(ctx) -> { 


    String statusParam = ctx.body();

    Status status = Status.valueOf(statusParam);
    List<Reimbursement> reim = reimbursementService.getReimbursementByStatus(status); //DELETE IF NECESSARY
    Gson gson = new Gson();
    String json = gson.toJson(reim);


    if(reim != null) {

        ctx.result(json);
        ctx.status(HttpCode.OK);

    } else {
        ctx.status(HttpCode.OK);
        ctx.result(json);
    } 
};









}



