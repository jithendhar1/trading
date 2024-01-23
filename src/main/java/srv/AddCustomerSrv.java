package srv;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Imp.CustomerImp;
import java.io.IOException;

@WebServlet("/AddUser")
public class AddCustomerSrv extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String status="Adding fail";
            String userID = request.getParameter("userID");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phno = request.getParameter("phno");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");

            CustomerImp cisimp =new CustomerImp();
       	 status =cisimp.addUser(userID,username, email, password, phno, firstname, lastname);
            

       	   RequestDispatcher rd = request.getRequestDispatcher("login.jsp?message=" + status);
       rd.forward(request, response);
       
    }
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

       doGet(request, response);
       }
       }