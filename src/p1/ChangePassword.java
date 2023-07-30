package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ChangePassword extends HttpServlet 
{
	private HttpSession session;

	protected void service(HttpServletRequest request, HttpServletResponse response)  
	
	{
	String newpwd=request.getParameter("newpwd");
	session=request.getSession();
	String accno=(String)session.getAttribute("accno");
	Model m=new Model();
	m.setAccno(accno);
	m.setNewpwd(newpwd);
	m.connectDataBase();
	boolean status=m.changePassword();
	if(status==true)
	{
		try {
			response.sendRedirect("/BankApplication/successchangepwd.jsp");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
    }
	else
	{
		try 
		{
			response.sendRedirect("/BankApplication/failchangepwd.jsp");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	}
}

