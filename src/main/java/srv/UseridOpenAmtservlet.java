package srv;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ROIDAO;

@WebServlet("/UseridOpenAmtservlet")
public class UseridOpenAmtservlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the userID parameter from the request
        String userID = request.getParameter("userID");
        System.out.println("in UseridOpenAmtservlet ");
        // Call the DAO method to get the amount
        String amount = ROIDAO.getAmountByuserId(userID);

        // Set the response content type
        response.setContentType("text/plain");

        // Write the amount to the response
        PrintWriter out = response.getWriter();
        out.print(amount);
    }
}
