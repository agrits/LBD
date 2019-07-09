package pl.fis.artur.kasza.lbd;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CSVServlet
 */
@WebServlet("/csv")
public class CSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CSVServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String header = "University Name; Quality of courses & teaching?(Average);Contact with teachers? (Average);Inclusion of work/practical experience?(Average); Average;\n";
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"statistics.csv\"");
		
		OutputStream outputStream = response.getOutputStream();
        outputStream.write(header.getBytes());
        
		Object surveys = request.getServletContext().getAttribute("surveys");
		if(surveys!=null) {
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<Double>> statistics = Calculator.calculate((ArrayList<Survey>)surveys);
		
		 try
		    { 
		        for(String university : statistics.keySet()) {
		        	ArrayList<Double> currentAverages = statistics.get(university);
		        	outputStream.write(String.format("%s; %s; %s; %s; %s;", 
		        			university, 
		        			currentAverages.get(0), 
		        			currentAverages.get(1), 
		        			currentAverages.get(2), 
		        			currentAverages.get(3)).getBytes()); 
		        }
		        outputStream.flush();
		        outputStream.close();
		    }
		    catch(Exception e)
		    {
		        System.out.println(e.toString());
		    }
		}
		
	}


}
