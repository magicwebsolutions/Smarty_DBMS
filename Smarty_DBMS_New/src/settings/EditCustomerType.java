package settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditCustomerType
 */
@WebServlet("/EditCustomerType")
public class EditCustomerType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCustomerType() {
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
		
		response.getWriter().append("Served atsdfsfsdfd: ").append(request.getContextPath());
		String Event = request.getParameter("EVENT");
		System.out.println("Event--->"+Event);
		HashMap<String, String> EditCustomerTypeDtlsMap = new HashMap<String, String>();
		String EditCustTypeStatus ;
		EditCustomerTypeDAO editCustTypeObj= new EditCustomerTypeDAO();
		
		if(Event.equalsIgnoreCase("EDITCUSTOMERTYPE")){
			String Edit_CustomerTypeID = request.getParameter("edit_custType_id");
			String Edit_CustomerTypeName = request.getParameter("edit_custType_name");
			String Edit_CustomerTypeDescription = request.getParameter("edit_custType_description");
			
			
			EditCustomerTypeDtlsMap.put("Event_key", Event);
			EditCustomerTypeDtlsMap.put("Edit_CustTypeName_key", Edit_CustomerTypeName);
			EditCustomerTypeDtlsMap.put("Edit_CustTypeDescription_key",Edit_CustomerTypeDescription);
			EditCustomerTypeDtlsMap.put("Edit_CustTypeID_key",Edit_CustomerTypeID);
			EditCustTypeStatus = editCustTypeObj.editCustomerTypeDtls(EditCustomerTypeDtlsMap);
			if(EditCustTypeStatus.equalsIgnoreCase("success")){
				request.setAttribute("returnFlag_EditCustomerType", EditCustTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_CustomerTypes.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag_EditCustomerType", EditCustTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_CustomerTypes.jsp").forward(request,response);	
				}
		}
		
		doGet(request, response);
	}

}
