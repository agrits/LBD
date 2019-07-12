package pl.fis.artur.kasza.lbd.beans;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.fis.artur.kasza.lbd.models.Survey;
import pl.fis.artur.kasza.lbd.utils.Utils;

@Stateless
public class SurveyManagerBean {
	
	public int commitSurvey(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = Utils.prepareSession(request);
		
		if(request.getSession().getAttribute("submitted")!=null) {
			response.getWriter().append("It's not possible to submit more than one survey.");
			return 0;
		}
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
				+ "Inclusion of work/practical experience?: %s\n\n", answers.get(0), answers.get(1), answers.get(2)));
			
		
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
		session.setAttribute("submitted", true);
		return 1;
	}
}
