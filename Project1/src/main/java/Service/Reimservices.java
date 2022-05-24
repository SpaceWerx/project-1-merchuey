package Service;

import models.Status;		
import models.User;
import repositories.ReimbursementDAO;
import repositories.UserDAO;

import java.util.ArrayList;
import java.util.List;

import mockdata.MockReimbursementData;
import models.Reimbursement;
import models.Role;

public class Reimservices {

			public ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
			public UserService rService = new UserService();
			public static List<MockReimbursementData> mockData = new ArrayList<>();
			public static ArrayList<Reimbursement> reimbursements = new ArrayList<>();	
			public static void clearData() {	
				reimbursements.clear();
			}

	public Reimbursement update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {	

			User manager = rService.getUserById(resolverId);
			
			if(manager.getRole() != Role.Manager) {
				throw new RuntimeException("There was an error processing this reimbursement, please try again.");
			}else {
				
				unprocessedReimbursement.setResolver(resolverId);
				unprocessedReimbursement.setStatus(updatedStatus);
				
				reimbursementDAO.update(unprocessedReimbursement);
				
				return unprocessedReimbursement;
			}
			
	}
		
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			public List<Reimbursement> getPendingReimbursements() { 

				return reimbursementDAO.getByStatus(Status.Pending);
	}
	////////////////////////////////////////////////////////////////////
		
			public List<Reimbursement> getResolvedReimbursements(){
				
				List<Reimbursement> resolvedReimbursements = new ArrayList<>();
				
//			for (Reimbursement reimbursement : reimbursements) {
//				
//				if (reimbursement.getStatus() == Status.Aproved || reimbursement.getStatus() == Status.Denied) {
//					resolvedReimbursements.add(reimbursement);
//					}
//				}
				resolvedReimbursements.addAll(reimbursementDAO.getByStatus(Status.Approved));
				resolvedReimbursements.addAll(reimbursementDAO.getByStatus(Status.Denied));
				return resolvedReimbursements;
				
				
			}
		
	/////////////////////////////////////////////////////////////////////////////		
			
		public int submitReimbursement (Reimbursement reimbursementToBeSubmitted) {
			

		
			User employee = rService.getUserById(reimbursementToBeSubmitted.getAuthor());
		
			if(employee.getRole() != Role.Employee) {
				
				throw new IllegalArgumentException("Managers cannot submit reimbursement requests.");
			} else {
				reimbursementToBeSubmitted.setStatus(Status.Pending);
				
		
				return reimbursementDAO.create(reimbursementToBeSubmitted);
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public List<Reimbursement> getReimbursementsByAuthor(int userId) {
			
			List<Reimbursement> userReimbursements = new ArrayList<>();
			
				for(Reimbursement r : reimbursements) {
					if (r.getAuthor() == userId || r.getResolver() == userId) {
					}
				}
				return userReimbursements;
		}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Reimbursement updateManager(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {
		
			getUserService();//DELETE IF NECESSARY
			User manager = rService.getUserById(resolverId);
			
			if(manager.getRole() != Role.Manager) {
				
				throw new IllegalArgumentException("An Employee cannot process reimbursement requests.");
			}else {
				unprocessedReimbursement.setResolver(resolverId);
				unprocessedReimbursement.setStatus(updatedStatus);
				
				reimbursementDAO.update(unprocessedReimbursement);
				
				return unprocessedReimbursement;
			}
	}
	////////////////////////////////////////
	public Reimbursement getReimbursementById(int id) {return ReimbursementDAO.getReimbursementById(id);}
		
	public List<Reimbursement> getReimbursementByAuthor(int userId) {
		return reimbursementDAO.getReimbursementsByUser(userId);
	}


	public UserService getUserService() {
		return rService;
	}


	public void setUserService(UserService userService) {
		this.rService = userService;
	}
		
		}

