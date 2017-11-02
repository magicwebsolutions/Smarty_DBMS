package settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddCustomerType
 */
@WebServlet("/AddCustomerType")
public class AddCustomerType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerType() {
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
		String Event = request.getParameter("EVENT");
		HashMap<String, String> AddNewCustomerTypeDtlsMap = new HashMap<String, String>();
		String InsertNewCustTypeStatus ;
		AddCustomerTypeDAO addCustTypeObj= new AddCustomerTypeDAO();
		
		if(Event.equalsIgnoreCase("ADDNEWCUSTOMERTYPE")){
			String CustomerTypeName = request.getParameter("custType_name");
			String CustomerTypeDescrption= request.getParameter("custType_description");
			
			AddNewCustomerTypeDtlsMap.put("Event_key", Event);
			AddNewCustomerTypeDtlsMap.put("CustTypeName_key", CustomerTypeName);
			AddNewCustomerTypeDtlsMap.put("CustTypeDescription_key", CustomerTypeDescrption);
			InsertNewCustTypeStatus = addCustTypeObj.addNewCustomerTypeDtls(AddNewCustomerTypeDtlsMap);
			if(InsertNewCustTypeStatus.equalsIgnoreCase("success")){
				request.setAttribute("returnFlag_AddNewCustomerType", InsertNewCustTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_CustomerTypes.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag_AddNewCustomerType", InsertNewCustTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_CustomerTypes.jsp").forward(request,response);	
				}
		}
		
		doGet(request, response);
	}

}
