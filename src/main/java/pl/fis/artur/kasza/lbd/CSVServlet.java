package pl.fis.artur.kasza.lbd;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

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
		ArrayList<String> keys = new ArrayList<String>(statistics.keySet());
		Collections.sort(keys, (String key1, String key2) -> Double.compare(statistics.get(key2).get(3), statistics.get(key1).get(3)));
		 try
		    { 
		        for(String university : keys) {
		        	ArrayList<Double> currentAverages = statistics.get(university);
		        	outputStream.write(String.format("%s; %s; %s; %s; %s;\n", 
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
