package pl.fis.artur.kasza.lbd.filters;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.fis.artur.kasza.lbd.models.User;

/**
 * Servlet Filter implementation class SurveyAdminFilter
 */
@WebFilter(filterName = "/SurveyAdminFilter", urlPatterns = {"/monitor"})
public class SurveyAdminFilter implements Filter {
	private String[] ROLES_PERMITTED = {"ADMIN"};


	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null && Arrays.asList(ROLES_PERMITTED).contains(user.getRole())) {
			chain.doFilter(request, response);
		}
		else {
			response.getWriter().append("<h3>Forbidden</h3><br>").append("<a href='login.html'>Login</a><br>");
		}
		
	}


}
