package pl.fis.artur.kasza.lbd.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {
	public static HttpSession prepareSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.isNew()) {
			String remoteAddr = request.getRemoteAddr();
			Logger logger = Logger.getLogger("http-session logger");
			logger.info("ip: "+remoteAddr);
			logger.info("Preffered language: "+request.getLocale());
		}
		return session;
	}
}
