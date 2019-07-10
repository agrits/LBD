package pl.fis.artur.kasza.lbd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

/**
 * Servlet implementation class StatisticsServlet
 */
@WebServlet("/average-statistics")
public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticsServlet() {
    	super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utils.prepareSession(request);
		PrintWriter pw = response.getWriter();
		
		
		ServletContext context = request.getServletContext();
		
		@SuppressWarnings("unchecked")
		ArrayList<Survey> surveys = (ArrayList<Survey>) context.getAttribute("surveys");
		
		if(surveys != null) {
			HashMap<String, ArrayList<Double>> scores;
			scores = Calculator.calculate(surveys);
			
			
			pw.append("<a target='_blank' href='/lbd/csv'>Download CSV</a>");
			for(String university : scores.keySet()) {
				ArrayList<Double> currentScores = scores.get(university);
				pw.append(String.format("<h3>%s</h3>", university));
				for(int i = 0; i<3; i++) {
					pw.append(String.format("\"%s\": %.2f<br>", Questions.questions[i], currentScores.get(i)));
				}
				pw.append(String.format("Overall average: %.2f", currentScores.get(3)));
				
				
			}
		}
		
	}


}
