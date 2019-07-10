package pl.fis.artur.kasza.lbd;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {
	public static void prepareSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.isNew()) {
			String remoteAddr = request.getRemoteAddr();
			Logger logger = Logger.getLogger("http-session logger");
			logger.info("ip: "+remoteAddr);
			logger.info("Preffered language: "+request.getLocale());
		}
	}
}
