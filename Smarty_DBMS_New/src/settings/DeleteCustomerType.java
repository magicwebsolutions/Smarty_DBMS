package settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCustomerType
 */
@WebServlet("/DeleteCustomerType")
public class DeleteCustomerType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCustomerType() {
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
		HashMap DeleteCustomerTypeDtlsMap = new HashMap();
		String DeleteCustTypeStatus ;
		DeleteCustomerTypeDAO deleteCustTypeObj= new DeleteCustomerTypeDAO();
		
		if(Event.equalsIgnoreCase("DELETECUSTOMERTYPE")){
			String delete_CustType_Id = request.getParameter("delete_CustType_Id");
			System.out.println("delete_CustType_Id--DAO-->"+delete_CustType_Id);
			DeleteCustomerTypeDtlsMap.put("Event_key", Event);
			DeleteCustomerTypeDtlsMap.put("Delete_CustTypeId_key", delete_CustType_Id);
			DeleteCustTypeStatus = deleteCustTypeObj.deleteCustomerTypeDtls(DeleteCustomerTypeDtlsMap);
			if(DeleteCustTypeStatus.equalsIgnoreCase("success")){
				request.setAttribute("returnFlag_DeleteCustomerType", DeleteCustTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_CustomerTypes.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag_DeleteCustomerType", DeleteCustTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_CustomerTypes.jsp").forward(request,response);	
				}
		}
		
		doGet(request, response);
	}

}
