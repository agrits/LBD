package pl.fis.artur.kasza.lbd.listeners;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListenerWithLogging
 *
 */
@WebListener
public class SessionListenerWithLogging implements HttpSessionListener {
	private Logger logger;
    /**
     * Default constructor. 
     */
    public SessionListenerWithLogging() {
    	super();
    	logger = Logger.getLogger("http-session logger");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	HttpSession session = se.getSession();
        logger.info("Session created at "+LocalDateTime.now());
        ServletContext ctx = session.getServletContext();
        
        @SuppressWarnings("unchecked")
		ArrayList<HttpSession> sessions = (ArrayList<HttpSession>) ctx.getAttribute("sessions");
        sessions.add(session);
        ctx.setAttribute("sessions", sessions);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	HttpSession session = se.getSession();
        logger.info("Session ended at "+LocalDateTime.now());
    }
	
}
