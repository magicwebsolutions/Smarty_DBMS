package dashboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String event ="";
		StringBuffer dashboard_ResponseData = new StringBuffer();
		
		try{
			event = request.getParameter("Event");
			
			if(event.equalsIgnoreCase("DASHBOARD")){
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				DashboardDAO dasboardObj = new DashboardDAO();				
				dashboard_ResponseData.append(dasboardObj.ListDashboardData());
				out.println(dashboard_ResponseData.toString());			
			}
			
		}catch(Exception e){
			System.out.println("Something went wrong...."+e);
			e.printStackTrace();
			
		}
		doGet(request, response);
	}

}
