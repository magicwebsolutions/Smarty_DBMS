package settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddItemType
 */
@WebServlet("/AddItemType")
public class AddItemType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemType() {
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
		HashMap<String, String> AddNewItemTypeDtlsMap = new HashMap<String, String>();
		String InsertNewItemTypeStatus ;
		AddItemTypeDAO addItemTypeObj= new AddItemTypeDAO();
		
		if(Event.equalsIgnoreCase("ADDNEWITEMTYPE")){
			String ItemTypeName = request.getParameter("itemType_name");
			String ItemTypeDescrption= request.getParameter("itemType_description");
			
			AddNewItemTypeDtlsMap.put("Event_key", Event);
			AddNewItemTypeDtlsMap.put("ItemTypeName_key", ItemTypeName);
			AddNewItemTypeDtlsMap.put("ItemTypeDescription_key", ItemTypeDescrption);
			InsertNewItemTypeStatus = addItemTypeObj.addNewItemTypeDtls(AddNewItemTypeDtlsMap);
			if(InsertNewItemTypeStatus.equalsIgnoreCase("success")){
				request.setAttribute("returnFlag_AddNewItemType", InsertNewItemTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_ItemTypes.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag_AddNewItemType", InsertNewItemTypeStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_ItemTypes.jsp").forward(request,response);	
				}
		}
		
		doGet(request, response);
	}

}
