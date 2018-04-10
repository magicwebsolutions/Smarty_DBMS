package dashboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
		System.out.println("6666666666666666666666666666666666666");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("555555555555555555555555555555555555");
		String event ="";
		StringBuffer dashboard_ResponseData = new StringBuffer();
		
		try{
			event = request.getParameter("Event");
			
			if(event.equalsIgnoreCase("DASHBOARD")){
				System.out.println("11111111111111111111111111111111111111111111111111111111111111");
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				System.out.println("222222222222222222222222222222222222222222222222222222222222222");
				DashboardDAO dasboardObj = new DashboardDAO();				
				System.out.println("3333333333333333333333333333333333333333333");
				dashboard_ResponseData.append(dasboardObj.ListDashboardData());
				out.println(dashboard_ResponseData.toString());			
			}
			else if(event.equalsIgnoreCase("MAILTRIGGER")){
				System.out.println("444444444444444444444444444444444444444444444444444444");
				String result = DashboardDAO.mailProcess();
				System.out.println("DOPOST::result::"+result);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Header.jsp");
				request.setAttribute("MAIL_RESULT", result);
				dispatcher.forward(request, response);
				
			}
			
		}catch(Exception e){
			System.out.println("Something went wrong...."+e);
			e.printStackTrace();
			
		}
		doGet(request, response);
	}

}
