package Transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import settings.ListCustomerDAO;

/**
 * Servlet implementation class Transaction
 */
@WebServlet("/Transaction")
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served atsssssssssssss: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String event =request.getParameter("Event");
		StringBuffer transactionCreditBuffer = new StringBuffer();			
		StringBuffer getCustomersBuffer = new StringBuffer();
		StringBuffer getItemsBuffer = new StringBuffer();
		StringBuffer addNewTransactionReqBuffer = new StringBuffer();
		HashMap<String, String> AddNewTransactionMap = new HashMap<String, String>();
		TransactionDAO TransactionDAO = new TransactionDAO();
		
		try{			
			if(event.equalsIgnoreCase("CREDITTO_CURRENTDAY")){
				String DateToPass = request.getParameter("datePassed");
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				transactionCreditBuffer.append(TransactionDAO.ListTransaction(DateToPass));
				out.println(transactionCreditBuffer.toString());			
			}
			
			else if(event.equalsIgnoreCase("GET_CUSTOMERS")){
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				getCustomersBuffer.append(TransactionDAO.getCustomers());
				out.println(getCustomersBuffer.toString());			
			}
			else if(event.equalsIgnoreCase("GET_ITEMS")){
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				getItemsBuffer.append(TransactionDAO.getItems());
				out.println(getItemsBuffer.toString());			
			}
			else if(event.equalsIgnoreCase("ADDNEWCREDITTOTRANSACTION")){
				String TransactionDate = request.getParameter("Trans_date");
				String TransactionAmount = request.getParameter("Trans_Amount");
				String TransactionDescription = request.getParameter("Trans_Description");
				String TransactionCustIID = request.getParameter("getCustomerDropDown");
				String TransactionItemID = request.getParameter("getItemsDropDown");
				
				
				System.out.println(TransactionDate+"~~"+TransactionAmount+"~~"+TransactionDescription+"~~"+TransactionItemID+"~~"+TransactionCustIID);
				AddNewTransactionMap.put("TransactionDate_key", TransactionDate);
				AddNewTransactionMap.put("TransactionAmount_key", TransactionAmount);
				AddNewTransactionMap.put("TransactionDescription_key", TransactionDescription);
				AddNewTransactionMap.put("TransactionItemID_key", TransactionItemID);
				AddNewTransactionMap.put("TransactionCustIID_key", TransactionCustIID);
				String AddTransactionStatus = TransactionDAO.addNewCreditToTransaction(AddNewTransactionMap);
				if(AddTransactionStatus.equalsIgnoreCase("success")){
					request.setAttribute("returnFlag_AddNewCRTransaction", AddTransactionStatus);
					request.getRequestDispatcher("/JSP_Pages/Transaction_CreditTo.jsp").forward(request,response);			
				}
				else{
					request.setAttribute("returnFlag_AddNewCRTransaction", AddTransactionStatus);
					request.getRequestDispatcher("/JSP_Pages/Transaction_CreditTo.jsp").forward(request,response);	
					}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
	}

}
