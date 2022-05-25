package Controller;

import javax.net.ssl.SSLEngineResult.Status;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import Models.Reimbursement;
import Service.Reimservices;
import Service.User_Services;

public class ReimbursementController {
	
	ObjectMapper objectMapper = new ObjectMapper();
	Reimservices reimbursementService = new Reimservices();
	User_Services user_Service = new User_Services();
///////////////////////////////////////////////////////////////////////////////////////////
	
	public void handleSubmit(Context ctx) {
	
		try {
			String input = ctx.body();
			
			Reimbursement reimbursement = objectMapper.readValue(input, Reimbursement.class);
			
			int id = reimbursementService.submitReimbursement(reimbursement);
			
			if(id != 0) {
				
				ctx.status(HttpCode.CREATED);
				ctx.result(""+id);
			
			} else {
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Reimbursement submission was unsuccessful.");
				
			}
		} catch (Exception e) {
			
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			
			if(!e.getMessage().isEmpty()) {
				ctx.result(e.getMessage());
			}
			e.printStackTrace();
		}
	}
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
				
				Reimbursement processedReimbursement = reimbursementService.update(reimbursement, userId, Status.valueOf(statusInput));
				
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
public void handleGetReimbursements (Context ctx) {
	if(ctx.queryParam("author") != null) {
		handleGetReimbursementsByAuthor(ctx);
	} else if(ctx.queryParam("status") != null) {
		handleGetReimbursmentByStatus(ctx);
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////
public void handleGetReimbursmentByStatus(Context ctx) {
	
	try {
		String statusParam = ctx.queryParam("status");
		
		Status status = Status.valueOf(statusParam);
		
		if(status == Status.Pending) {
			ctx.status(HttpCode.OK);
			ctx.json(reimbursementService.getPendingReimbursements());
		} else {
			ctx.status(HttpCode.OK);
			ctx.json(reimbursementService.getResolvedReimbursements());
		}
	} catch(Exception e) {
		ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
		
		if(!e.getMessage().isEmpty()) {
			ctx.result(e.getMessage());
		}
		e.printStackTrace();
	}
}
/////////////////////////////////////////////////////////////////////////////

public void handleGetReimbursementById(Context ctx) {

		try {
			String idParam = ctx.pathParam("id");
			
			int id = Integer.parseInt(idParam);
			
			Reimbursement reimbursement = reimbursementService.getReimbursementById(id);
		
			if(reimbursement != null) {
				ctx.status(HttpCode.OK);
				ctx.json(reimbursement);
			} else {
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Could not retrieve the reimbursement");
			}
			
		} catch(Exception e){ 
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			
			if(!e.getMessage().isEmpty()) {
				ctx.result(e.getMessage());
			}
			e.printStackTrace();
		}
}
//////////////////////////////////////////////////////////////////////////////////////////////

public void handleGetReimbursementsByAuthor(Context ctx) {
	
	try {
		
		String idParam = ctx.queryParam("author");
		
		if(idParam != null) {
			int id = Integer.parseInt(idParam);
			
			if(user_Service.checkUserExistsById(id)) {
				ctx.status(HttpCode.OK);
				
				ctx.json(reimbursementService.getReimbursementsByAuthor(id));
			} else {
				ctx.status(HttpCode.NOT_FOUND);
				ctx.result("Unable to retrieve reimbursements, current user is not in the database");
			}
		} else {
			
			ctx.status(HttpCode.BAD_REQUEST);
			ctx.result("Missing Current User header");
		}
		
	} catch(Exception e) {
		
	
		ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
		
		if(!e.getMessage().isEmpty()) {
			ctx.result(e.getMessage());
		}
		e.printStackTrace();
	}
	
}












}



