package srv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DepositDAO;
import utility.DBUtil;

@WebServlet("/DepositeStatus")
public class DepositeStatus extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Your code to handle GET requests
		commonLogic(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		commonLogic(request, response);
	}

	private void commonLogic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String status="Adding fail";
  
            String userID = request.getParameter("userID");         
            String Amount = request.getParameter("amount");
            String depositeid = request.getParameter("depositID");
            Connection conn = DBUtil.provideConnection();
            PreparedStatement ps = null;
            PreparedStatement psUpdateBank = null;
            
            Connection conn1 = DBUtil.provideConnection();
            PreparedStatement ps1 = null;
            PreparedStatement psUpdateBank1 = null;
            
            
	try {
		
		psUpdateBank1 = conn.prepareStatement("update deposit set status=1 where DepositTransactionID=?");
		psUpdateBank1.setString(1, depositeid);
		int updateResult1 = psUpdateBank1.executeUpdate();
		if (updateResult1 > 0) {
        
		
        psUpdateBank = conn.prepareStatement("UPDATE customeraccdetails SET Amount = Amount + ? WHERE userID = ?");
        psUpdateBank.setString(1, Amount); // Assuming Amount is a String
        psUpdateBank.setString(2, userID);

        int updateResult = psUpdateBank.executeUpdate();

        if (updateResult > 0) {
            
        	Connection con1 = DBUtil.provideConnection();
        	int tempamount = Integer.parseInt(Amount);
        	if(tempamount>=500)
        	{
        	    int count= DepositDAO.totalCountDeposites(userID);
        	    if(count==1)
        	    {
        	    	String reffereduserid = DepositDAO.referralID(userID);
        	    	int Tenper = (int) (0.1 * tempamount);
        	    	String Tenperamount = String.valueOf(Tenper);
        	    	ps =  conn.prepareStatement("UPDATE customeraccdetails SET Amount = Amount + ? WHERE userID = ?");
        	    	status = " Added Successfully!";
                    ps.setString(1, Tenperamount);
                    ps.setString(2, reffereduserid);
                    
                    int k = psUpdateBank.executeUpdate();

                    if (k > 0) {
                    	status = " updating bankdetails";
                        /*response.sendRedirect("deposit_user.jsp");*/
                    }
            		}
        	}
            response.sendRedirect("deposit_user.jsp");
        }
		}
	}catch (Exception e) {
		e.printStackTrace();
		response.sendRedirect("error.jsp");
	} finally {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Handle the exception if needed
		}
	}
}
}


