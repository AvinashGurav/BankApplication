package p1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GetStatement extends HttpServlet 
{
	 
private HttpSession session;
private ArrayList al;

protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	session=request.getSession();
	String accno=(String)session.getAttribute("accno");
	Model m=new Model();
	m.connectDataBase();
	m.setAccno(accno);
	al=m.getStatement();
	session.setAttribute("al",al);
response.sendRedirect("/BankApplication/displaystatement.jsp");
	
	
	
}
}
