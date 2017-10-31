package settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddCustomer
 */
@WebServlet("/AddCustomer")
public class AddCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomer() {
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
		response.getWriter().append("Served aSSSSSSt:::::::::::"+request).append(request.getContextPath());
		String Event = request.getParameter("EVENT");
		HashMap AddNewCustomerDtlsMap = new HashMap();
		String InsertNewCustStatus ;
		AddCustomerDAO addCustObj= new AddCustomerDAO();
		
		if(Event.equalsIgnoreCase("ADDNEWCUSTOMER")){
			String CustomerName = request.getParameter("cust_name");
			String CustomerPhone = request.getParameter("cust_phone");
			String CustomerAddress = request.getParameter("cust_Address");
			String CustomerType = request.getParameter("Cust_type");
			
			AddNewCustomerDtlsMap.put("Event_key", Event);
			AddNewCustomerDtlsMap.put("CustName_key", CustomerName);
			AddNewCustomerDtlsMap.put("CustPhone_key", CustomerPhone);
			AddNewCustomerDtlsMap.put("CustAddress_key", CustomerAddress);
			AddNewCustomerDtlsMap.put("CustType_key", CustomerType);
			InsertNewCustStatus = addCustObj.addNewCustomerDtls(AddNewCustomerDtlsMap);
			if(InsertNewCustStatus.equalsIgnoreCase("success")){
				request.setAttribute("returnFlag_AddNewCustomer", InsertNewCustStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag_AddNewCustomer", InsertNewCustStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings.jsp").forward(request,response);	
				}
		}
		
		doGet(request, response);
	}

	
}
