package settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditCustomer
 */
@WebServlet("/EditCustomer")
public class EditCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCustomer() {
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
		System.out.println("Event--->"+Event);
		HashMap EDITCUSTOMERDtlsMap = new HashMap();
		String InsertNewCustStatus ;
		EditCustomerDAO addCustObj= new EditCustomerDAO();
		
		if(Event.equalsIgnoreCase("EDITCUSTOMER")){
			String Edit_CustomerName = request.getParameter("edit_cust_name");
			String Edit_CustomerPhone = request.getParameter("edit_cust_phone");
			String Edit_CustomerAddress = request.getParameter("edit_cust_Address");
			String Edit_CustomerType = request.getParameter("edit_Cust_type");
			String Edit_CustomerID = request.getParameter("edit_cust_id");
			
			EDITCUSTOMERDtlsMap.put("Event_key", Event);
			EDITCUSTOMERDtlsMap.put("Edit_CustName_key", Edit_CustomerName);
			EDITCUSTOMERDtlsMap.put("Edit_CustPhone_key",Edit_CustomerPhone);
			EDITCUSTOMERDtlsMap.put("Edit_CustAddress_key", Edit_CustomerAddress);
			EDITCUSTOMERDtlsMap.put("Edit_CustType_key", Edit_CustomerType);
			EDITCUSTOMERDtlsMap.put("Edit_CustID_key", Edit_CustomerID);
			InsertNewCustStatus = addCustObj.editCustomerDtls(EDITCUSTOMERDtlsMap);
			if(InsertNewCustStatus.equalsIgnoreCase("success")){
				request.setAttribute("returnFlag_EDITCUSTOMER", InsertNewCustStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag_EDITCUSTOMER", InsertNewCustStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings.jsp").forward(request,response);	
				}
		}
		
		doGet(request, response);
	}

}
