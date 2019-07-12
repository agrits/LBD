package pl.fis.artur.kasza.lbd.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
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

}
