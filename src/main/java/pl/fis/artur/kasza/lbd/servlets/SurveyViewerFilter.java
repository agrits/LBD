package pl.fis.artur.kasza.lbd.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.fis.artur.kasza.lbd.models.User;

/**
 * Servlet Filter implementation class SurveyViewerFilter
 */

@WebFilter(filterName = "/SurveyViewerFilter", urlPatterns = "*statistics")

public class SurveyViewerFilter implements Filter {

	private String[] ROLES_PERMITTED = {"STATISTIC_VIEWER", "STATISTIC_MANAGER", "ADMIN"};
    /**
     * Default constructor. 
     */
    public SurveyViewerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null && Arrays.asList(ROLES_PERMITTED).contains(user.getRole())) {
			chain.doFilter(request, response);
		}
		else {
			//TODO Forbidden message
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
