package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Resetpwd extends HttpServlet 
{

	private HttpSession session;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String pwd=request.getParameter("npwd");
		session=request.getSession();
		String email=(String)session.getAttribute("email");
	     Model m=new Model();
	     m.setEmail(email);
	     m.setPwd(pwd);
	     m.connectDataBase();
	    boolean status=m.resetPwd();
	    if(status==true)
	    {
	    	response.sendRedirect("/BankApplication/successresetpwd.jsp");
	    }
	    else
	    {
	    	response.sendRedirect("/BankApplication/failresetpwd.jsp");
	    }
		
	}

}
