package settings;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListCustomerType
 */
@WebServlet("/ListCustomerType")
public class ListCustomerType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCustomerType() {
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
		String event ="";
		StringBuffer itemBuffer = new StringBuffer();
		
		try{
			event = request.getParameter("Event");			
			if(event.equalsIgnoreCase("LISTCUSTOMERTYPE")){
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				ListCustomerTypeDAO CustTypeDAO = new ListCustomerTypeDAO();				
				itemBuffer.append(CustTypeDAO.ListCustomerType());
				out.println(itemBuffer.toString());			
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		doGet(request, response);
	}

}
