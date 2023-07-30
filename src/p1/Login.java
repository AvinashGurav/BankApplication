package p1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custid=request.getParameter("custid");
		String pwd=request.getParameter("pwd");
		Model m= new Model();
		m.setCustid(custid);
		m.setPwd(pwd);
		m.connectDataBase();
		boolean status=m.login();
		if(status==true)
		{
			String accno=m.getAccno();
			HttpSession session=request.getSession();
			session.setAttribute("accno",accno);
			try {
				response.sendRedirect("/BankApplication/home.jsp");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				response.sendRedirect("/BankApplication/loginfail.jsp");	
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}

	}

}
