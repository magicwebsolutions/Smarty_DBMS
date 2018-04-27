package settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCustomer
 */
@WebServlet("/DeleteCustomer")
public class DeleteCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCustomer() {
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
		String Event = request.getParameter("EVENT");
		HashMap DeleteCustomerDtlsMap = new HashMap();
		String DeleteCustStatus ;
		DeleteCustomerDAO deleteCustObj= new DeleteCustomerDAO();
		
		if(Event.equalsIgnoreCase("DELETECUSTOMER")){
			String delete_Cust_Id = request.getParameter("delete_Cust_Id");
			
			DeleteCustomerDtlsMap.put("Event_key", Event);
			DeleteCustomerDtlsMap.put("Delete_CustId_key", delete_Cust_Id);
			DeleteCustStatus = deleteCustObj.deleteCustomerDtls(DeleteCustomerDtlsMap);
			if(DeleteCustStatus.equalsIgnoreCase("success")){
				request.setAttribute("returnFlag_DeleteCustomer", DeleteCustStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag_DeleteCustomer", DeleteCustStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings.jsp").forward(request,response);	
				}
		}
		
		doGet(request, response);
	}

}
