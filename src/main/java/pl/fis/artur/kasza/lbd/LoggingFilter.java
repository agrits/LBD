package pl.fis.artur.kasza.lbd;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoggingFilter
 */
@WebFilter(filterName = "/LoggingFilter", urlPatterns = {"*"},
		initParams = {
		        @WebInitParam(name = "username", value = "admin"),
		        @WebInitParam(name = "password", value = "admin")
		        })

public class LoggingFilter implements Filter {

    private Logger logger;
    private String username = "";
    private String password = "";
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
		
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
	            StringTokenizer st = new StringTokenizer(authHeader);
	            if (st.hasMoreTokens()) {
	                String basic = st.nextToken();

	                if (basic.equalsIgnoreCase("Basic")) {
	                    try {
	                        String credentials = new String(Base64.getDecoder().decode(st.nextToken()));
	                        System.out.println("credentials: " + credentials);
	                        int p = credentials.indexOf(":");
	                        if (p != -1) {
	                            String _username = credentials.substring(0, p).trim();
	                            String _password = credentials.substring(p + 1).trim();

	                            if (!username.equals(_username) || !password.equals(_password)) {
	                                unauthorized(response, "Bad credentials");
	                            }

	                            chain.doFilter(req, res);
	                        } else {
	                            unauthorized(response, "Invalid authentication token");
	                        }
	                    } catch (UnsupportedEncodingException e) {
	                        throw new Error("Couldn't retrieve authentication", e);
	                    }
	                }
	            }
	        } else {
	            unauthorized(response);
	        }
		
		
		
		
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
		username = fConfig.getInitParameter("username");
        password = fConfig.getInitParameter("password");
        String paramRealm = fConfig.getInitParameter("realm");
        if (paramRealm != null && paramRealm.length() > 0) {
            realm = paramRealm;
        	}
	}
	
	private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        response.sendError(401, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
}

}
