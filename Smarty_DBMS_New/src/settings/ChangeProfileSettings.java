package settings;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeProfileSettings
 */
@WebServlet("/ChangeProfileSettings")
public class ChangeProfileSettings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeProfileSettings() {
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
		HashMap EditProfileDtlsMap = new HashMap();
		String EditProfileDtlsStatus ;
		ChangeProfileSettingsDAO changeProfileObj= new ChangeProfileSettingsDAO();
		
		if(Event.equalsIgnoreCase("CHANGEPROFILES")){
			String CurrentPassword = request.getParameter("curr_pwd");
			String NewPassword = request.getParameter("new_pwd");
			String ConfirmNewPassword = request.getParameter("renew_pwd");
			
			EditProfileDtlsMap.put("Event_key", Event);
			EditProfileDtlsMap.put("Edit_ConfirmPassword_key", ConfirmNewPassword);
			EditProfileDtlsStatus = changeProfileObj.editProfileDtls(EditProfileDtlsMap);
			if(EditProfileDtlsStatus.equalsIgnoreCase("success")){
				request.setAttribute("returnFlag_EditProfileStatus", EditProfileDtlsStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_Profile.jsp").forward(request,response);			
			}
			else{
				request.setAttribute("returnFlag_EditProfileStatus", EditProfileDtlsStatus);
				request.getRequestDispatcher("/JSP_Pages/Settings_Profile.jsp").forward(request,response);	
				}
		}
		
		doGet(request, response);
	}

}
