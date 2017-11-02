package settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditItemType
 */
@WebServlet("/EditItemType")
public class EditItemType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditItemType() {
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
		HashMap<String, String> EditItemTypeDtlsMap = new HashMap<String, String>();
		String EditItemTypeStatus ;
		EditItemTypeDAO editItemTypeObj= new EditItemTypeDAO();
		
		if(Event.equalsIgnoreCase("EDITITEMTYPE")){
			String Edit_ItemTypeID = request.getParameter("edit_ItemType_id");
			String Edit_ItemTypeName = request.getParameter("edit_ItemType_name");
			String Edit_ItemTypeDescription = request.getParameter("edit_ItemType_description");
			
			
			EditItemTypeDtlsMap.put("Event_key", Event);
			EditItemTypeDtlsMap.put("Edit_ItemTypeName_key", Edit_ItemTypeName);
			EditItemTypeDtlsMap.put("Edit_ItemTypeDescription_key",Edit_ItemTypeDescription);
			EditItemTypeDtlsMap.put("Edit_ItemTypeID_key",Edit_ItemTypeID);
			EditItemTypeStatus = editItemTypeObj.editItemTypeDtls(EditItemTypeDtlsMap);
			if(EditItemTypeStatus.equalsIgnoreCase("success")){
				request.setAttribute("returnFlag_EditItemType", EditItemTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_ItemTypes.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag_EditItemType", EditItemTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_ItemTypes.jsp").forward(request,response);	
				}
		}
		
		doGet(request, response);
	}

}
