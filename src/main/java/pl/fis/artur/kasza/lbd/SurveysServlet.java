package pl.fis.artur.kasza.lbd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.IntStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SurveysServlet
 */
@WebServlet("/surveys")
public class SurveysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int successfulSubmitted = 0;
	@Override
	public void init(ServletConfig config) throws ServletException{
		successfulSubmitted = 0;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		successfulSubmitted++;
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String university = request.getParameter("university");
		String faculty = request.getParameter("faculty");
		String degree = request.getParameter("degree");
		ArrayList<String> answers = new ArrayList<String>();
		for(int i = 0; i<3; i++) {
			answers.add(request.getParameter("answer"+i));
		}
		
		//Print out answers
		response.getWriter()
		.append(String.format("Thanks %s %s for participation in our university survey.\n", firstName, lastName))
		.append(String.format("Your answers:\n"
				+ "University name: %s\n"
				+ "Faculty name: %s\n"
				+ "Degree: %s\n", university, faculty, degree))
		.append(String.format("Quality of courses & teaching?: %s\n"
				+ "Contact with teachers?: %s\n"
				+ "Inclusion of work/practical experience?: %s\n\n", answers.get(0), answers.get(1), answers.get(2)))
		.append(String.format("Successful survey submissions: %d", successfulSubmitted));
		
		Survey survey = new Survey(firstName, lastName, university, faculty, degree, answers);
		
		ServletContext context = request.getServletContext();
		//Save data in context
		
		@SuppressWarnings("unchecked")
		ArrayList<Survey> surveys = (ArrayList<Survey>) context.getAttribute("surveys");
		if(surveys == null) {
			surveys = new ArrayList<Survey>();
		}
		surveys.add(survey);
		context.setAttribute("surveys", surveys);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

			//Redirect get to html form
	        response.sendRedirect("/lbd/survey.html");
	        
	}

}
