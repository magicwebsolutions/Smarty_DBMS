package report;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import settings.ListCustomerDAO;

/**
 * Servlet implementation class Report
 */
@WebServlet("/Report")
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Report() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("INSIDE REPORT JAVA");
		String Event = "";
		StringBuffer summaryBuffer = new StringBuffer();
		StringBuffer CustomerInfoReportBuffer = new StringBuffer();
		try{
			Event = request.getParameter("Event");			
			if(Event.equalsIgnoreCase("GETSUMMARY")){
				String startDate = request.getParameter("fromdate");
				String endDate = request.getParameter("enddate");
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				ReportDAO ReportDAO = new ReportDAO();				
				summaryBuffer.append(ReportDAO.getIncomeExpenseSummary(startDate,endDate));
				out.println(summaryBuffer.toString());	
				
			}
			else if(Event.equalsIgnoreCase("GET_CUSTOMER_REPORT")){
				String custID = request.getParameter("customerId");
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				ReportDAO ReportDAO = new ReportDAO();				
				CustomerInfoReportBuffer.append(ReportDAO.getCustomerDetailedReport(custID));
				out.println(CustomerInfoReportBuffer.toString());	
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
