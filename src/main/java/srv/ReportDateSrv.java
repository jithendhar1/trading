
package srv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TransactionDAO;
import beans.TransactionBean;

@WebServlet("/ReportDateSrv")
public class ReportDateSrv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the selected transaction type from the request
        String Date = request.getParameter("Date");

        // Get the updated transactions based on the selected transaction type
        List<TransactionBean> transactions = TransactionDAO.getTransactionsByDate(Date);

        // Prepare the HTML content for the updated table
        StringBuilder htmlContent = new StringBuilder();
        for (TransactionBean task : transactions) {
            htmlContent.append("<tr>");
            htmlContent.append("<td>").append(task.getUserID()).append("</td>");
            htmlContent.append("<td>").append(task.getOpenamount()).append("</td>");
            htmlContent.append("<td>").append(task.getClosingamount()).append("</td>");
            htmlContent.append("<td>").append(task.getTransactiondate()).append("</td>");
            htmlContent.append("<td>").append(task.getStatus()).append("</td>");
            htmlContent.append("<td>").append(task.getApprovedby()).append("</td>");
            htmlContent.append("<td>").append(task.getTransactionID()).append("</td>");
            htmlContent.append("</tr>");
        }

        // Set the content type and write the updated table content as the response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(htmlContent.toString());
    }
}
