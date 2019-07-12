package pl.fis.artur.kasza.lbd.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import pl.fis.artur.kasza.lbd.beans.SurveyManagerBean;

/**
 * Servlet implementation class SurveysServlet
 */
@WebServlet("/surveys")
public class SurveysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int successfullySubmitted = 0;
	
	@Inject
	private SurveyManagerBean surveyManager;
	
	@Override
	public void init() throws ServletException{
		super.init();
		successfullySubmitted = 0;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		successfullySubmitted += surveyManager.commitSurvey(request, response);
		response.getWriter().append(String.format("Successful survey submissions: %d\n\n", successfullySubmitted));	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

			//Redirect get to html form
	        response.sendRedirect("/lbd/survey.html");
	        
	}

}
