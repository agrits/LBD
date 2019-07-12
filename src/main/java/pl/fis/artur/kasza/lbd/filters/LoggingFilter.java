package pl.fis.artur.kasza.lbd.filters;

import java.io.IOException;
import java.util.logging.Logger;

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

/**
 * Servlet Filter implementation class LoggingFilter
 */
@WebFilter(filterName = "/LoggingFilter", urlPatterns = {"*"})

public class LoggingFilter implements Filter {

    private Logger logger;
    private String username = "admin";
    private String password = "admin";
    private String realm = "Protected";

	/**
     * Default constructor. 
     */
    public LoggingFilter() {
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
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		long startTime = System.nanoTime();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		
	
		chain.doFilter(request, response);
		
		
		long finishTime = System.nanoTime();
		
		logger = Logger.getAnonymousLogger();
		logger.info(String.format("Processing time: %d microseconds, User IP: %s, Preferred Language: %s, Session ID: %s, format: %s", 
				(finishTime-startTime)/1000,
				request.getRemoteAddr(),
				request.getLocale(),
				request.getRequestedSessionId(),
				request.getHeader("Accept")));
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        response.sendError(401, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
}

}
