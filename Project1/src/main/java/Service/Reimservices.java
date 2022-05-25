package Service;

import Models.Status;	
	
import Models.Users;
import DAO.ReimbursementDAO;
import java.util.ArrayList;
import java.util.List;
import Models.Reimbursement;
import Models.Roles;

public class Reimservices {

			public ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
			public User_Services rService = new User_Services();
			public static ArrayList<Reimbursement> reimbursements = new ArrayList<>();	
			public static void clearData() {	
				reimbursements.clear();
			}

	public Reimbursement update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {	

			Users manager = rService.getUserbyId(resolverId);
			
			if(manager.getRole() != Roles.Manager) {
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
			

		
			Users employee = rService.getUserbyId(reimbursementToBeSubmitted.getAuthor());
		
			if(employee.getRole() != Roles.Employee) {
				
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
			Users manager = rService.getUserbyId(resolverId);
			
			if(manager.getRole() != Roles.Manager) {
				
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


	public User_Services getUserService() {
		return rService;
	}


	public void setUserService(User_Services userService) {
		this.rService = userService;
	}

	public Reimbursement update(Reimbursement reimbursement, int userId, javax.net.ssl.SSLEngineResult.Status valueOf) {
		// TODO Auto-generated method stub
		return null;
	}
		
		}
/////////////////////////////////////////////////////////////////////////////////////////


