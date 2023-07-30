package p1;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class ChangePasswordFilter implements Filter
{

   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		String newpwd=request.getParameter("newpwd");
		String confirmnewpwd=request.getParameter("confirmnewpwd");
		if(newpwd.equals(confirmnewpwd)==true)
		{
			chain.doFilter(request, response);
	
		}
		else
		{
			((HttpServletResponse)response).sendRedirect("/BankApplication/passworderror.jsp");
		}
		
	}

	
}
