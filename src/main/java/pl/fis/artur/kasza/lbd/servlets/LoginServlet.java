package pl.fis.artur.kasza.lbd.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.fis.artur.kasza.lbd.models.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.sendRedirect("/lbd/login.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		ServletContext sc = request.getServletContext();
		HttpSession session = request.getSession();
		User[] users = (User[]) sc.getAttribute("users");
		for(User user : users) {
			if(user.getUsername().contentEquals(username) && user.getPassword().contentEquals(password)) {
				user.setIp(request.getRemoteAddr());
				user.setLang(request.getLocale().toString());
				user.setSessionID(session.getId());
				session.setAttribute("user", user);
				pw.append("Logged in correctly.");
				pw.append(user.toString());
				return;
			}
		}
		pw.append("Login failed.\n");
		pw.append(username+"\n");
		pw.append(password+"\n");
		pw.append(Arrays.asList(users).toString());
		return;
		
	}

}
