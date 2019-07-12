package pl.fis.artur.kasza.lbd.listeners;

import java.util.ArrayList;
import java.util.stream.IntStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import pl.fis.artur.kasza.lbd.models.User;

@WebListener
public class ContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		
		String[] usernames = "aaa bbb ccc ddd eee fff".split(" ");
		String[] passwords = "aaa bbb ccc ddd eee fff".split(" ");
		String[] firstNames = "aaa bbb ccc ddd eee fff".toUpperCase().split(" ");
		String[] lastNames = "fff eee ddd ccc bbb aaa".toUpperCase().split(" ");
		int[] ids = IntStream.range(0, 6).toArray();
		String[] roles = {"ADMIN", "STATISTIC_VIEWER", "STATISTIC_MANAGER", "STATISTIC_VIEWER", "STATISTIC_MANAGER", "STATISTIC_VIEWER"};
		
		User[] users = new User[6];
		
		for(int i = 0; i<6; i++) {
			users[i] = new User(usernames[i], passwords[i], firstNames[i], lastNames[i], roles[i], ids[i]);
		}
		
		ctx.setAttribute("users", users);
		ctx.declareRoles(roles);
		ServletContextListener.super.contextInitialized(sce);
		
		
	}
}
