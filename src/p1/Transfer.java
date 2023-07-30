package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Transfer extends HttpServlet 
{
	
       
   
    
	
 


private HttpSession session;

protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String tamt=request.getParameter("tamt");
		session=request.getSession();
	    String accno=(String)session.getAttribute("accno");
	   Model m=new Model();
	   m.setAccno(accno);
	   m.setTamt(tamt);
	   m.connectDataBase();
	   boolean status=m.transfer();
	   m.statement();
	   if(status==true)
	   {
		   response.sendRedirect("/BankApplication/successtransfer.jsp");
	   }
	   else
	   {
		   response.sendRedirect("/BankApplication/failuretransfer.jsp");
	   }
	   
	   }
	   
	   

	}
