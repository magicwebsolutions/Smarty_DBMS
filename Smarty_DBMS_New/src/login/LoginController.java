package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String validation_Status = "FALSE";
		String UserName = null;
		String Password = null;
		String Repassword = null;
		String Hint = null;
		HashMap h_map = null;
		PrintWriter out = response.getWriter();
		if (request.getParameter("UserName") != null && request.getParameter("UserName") != ""
				&& request.getParameter("User_pwd") != null && request.getParameter("User_pwd") != "") {
			UserName = request.getParameter("UserName");
			Password = request.getParameter("User_pwd");
			h_map = new HashMap();
			h_map.put("flag", "Mainlogin");
			h_map.put("USER_NAME", UserName);
			h_map.put("USER_PWD", Password);
		} else {
			Hint = request.getParameter("hint");
			Password = request.getParameter("User_pwd1");
			Repassword = request.getParameter("User_pwd2");
			h_map = new HashMap();
			h_map.put("flag", "Sublogin");
			h_map.put("Hint", Hint);
			h_map.put("Password", Password);
			h_map.put("RePassword", Repassword);
		}
		LoginDAO login = new LoginDAO();
		validation_Status = login.validateUser(h_map);
		if (validation_Status == "TRUE") {
			request.setAttribute("status", "success");
			request.getRequestDispatcher("/JSP_Pages/Login.jsp").forward(request, response);

		} else if (validation_Status == "wronghint") {
			request.setAttribute("status", "wronghint");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}

		else if (validation_Status == "UPDATE_PASSWORD") {
			request.setAttribute("status", "updated");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}

		else {
			out.print("alert('User or password incorrect');");

			request.setAttribute("status", "failure");
			request.getRequestDispatcher("Login.jsp").forward(request, response);

		}
	}

}
