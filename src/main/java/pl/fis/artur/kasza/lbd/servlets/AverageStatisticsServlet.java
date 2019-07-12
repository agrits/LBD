package pl.fis.artur.kasza.lbd.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.fis.artur.kasza.lbd.calculators.Calculator;
import pl.fis.artur.kasza.lbd.models.Survey;
import pl.fis.artur.kasza.lbd.qualifiers.Average;
import pl.fis.artur.kasza.lbd.utils.Questions;
import pl.fis.artur.kasza.lbd.utils.Utils;


@WebServlet("/average-statistics")
public class AverageStatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	@Average
	private Calculator calculator;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AverageStatisticsServlet() {
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
			scores = calculator.calculate(surveys);
			
			
			pw.append("<a target='_blank' href='/lbd/average-csv'>Download CSV</a>");
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
