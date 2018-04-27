package settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteItemType
 */
@WebServlet("/DeleteItemType")
public class DeleteItemType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteItemType() {
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
		HashMap DeleteItemTypeDtlsMap = new HashMap();
		String DeleteCustTypeStatus ;
		DeleteItemTypeDAO deleteCustTypeObj= new DeleteItemTypeDAO();
		
		if(Event.equalsIgnoreCase("DELETEITEMTYPE")){
			String delete_ItemType_Id = request.getParameter("delete_ItemType_Id");
			DeleteItemTypeDtlsMap.put("Event_key", Event);
			DeleteItemTypeDtlsMap.put("Delete_ItemTypeId_key", delete_ItemType_Id);
			DeleteCustTypeStatus = deleteCustTypeObj.deleteItemTypeDtls(DeleteItemTypeDtlsMap);
			if(DeleteCustTypeStatus.equalsIgnoreCase("success")){
				request.setAttribute("returnFlag_DeleteItemType", DeleteCustTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_ItemTypes.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag_DeleteItemType", DeleteCustTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_ItemTypes.jsp").forward(request,response);	
				}
		}
		
		doGet(request, response);
	}

}
