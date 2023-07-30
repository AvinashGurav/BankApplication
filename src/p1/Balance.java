package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Balance extends HttpServlet 
{
	private HttpSession session;
	private String accno;
	private String balance;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 session=request.getSession();
		 accno=(String)session.getAttribute("accno");
		 Model m= new Model();
		 m.setAccno(accno);
		 m.connectDataBase();
		 
		 boolean status=m.checkBalance();
		 if(status==true)
		 {
			 balance=m.getBalance();
			 session.setAttribute("balance",balance);
			 try
			 {
				 response.sendRedirect("/BankApplication/successbalance.jsp"); 
			 }
			 catch(IOException e)
			 {
			 e.printStackTrace();
			 
			 }	 
		 }
		 else
		 {
			 try {
				 response.sendRedirect("/BankApp/failurebalance.jsp");
				 }
			 catch(IOException e)
			 {
				 e.printStackTrace();
			 }
		 }
	}
}
