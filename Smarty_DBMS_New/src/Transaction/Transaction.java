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
		HashMap<String, String> UpdateTransactionMap = new HashMap<String, String>();
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
				System.out.println("Helwoooooooooooooooooooooooooooooooooooooo");
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				getCustomersBuffer.append(TransactionDAO.getCustomers());
				out.println(getCustomersBuffer.toString());
				System.out.println("ddddddddddddddddddd-->"+getCustomersBuffer.toString());
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
				String TransactionNewCustName = request.getParameter("AddNewCustomer_Name");
				String TransactionNewCustPhone = request.getParameter("AddNewCustomer_Phone");
				String TransactionNewCustAddress = request.getParameter("AddNewCustomer_Address");
				String TransactionNewCustType  = request.getParameter("Cust_type");
				
				
				AddNewTransactionMap.put("TransactionDate_key", TransactionDate);
				AddNewTransactionMap.put("TransactionAmount_key", TransactionAmount);
				AddNewTransactionMap.put("TransactionDescription_key", TransactionDescription);
				AddNewTransactionMap.put("TransactionItemID_key", TransactionItemID);
				AddNewTransactionMap.put("TransactionCustIID_key", TransactionCustIID);

				AddNewTransactionMap.put("TransactionNewCustName_key", TransactionNewCustName);
				AddNewTransactionMap.put("TransactionNewCustPhone_key", TransactionNewCustPhone);
				AddNewTransactionMap.put("TransactionNewCustAddress_key", TransactionNewCustAddress);
				AddNewTransactionMap.put("TransactionNewCustType_key", TransactionNewCustType);
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
			else if(event.equalsIgnoreCase("EDITCREDITTOTRANSACTION")){
				String Upd_TransactionDate = request.getParameter("Edit_Trans_date");
				String Upd_TransactionAmount = request.getParameter("Edit_Trans_Amount");
				String Upd_TransactionDescription = request.getParameter("Edit_Trans_Description");
				String Upd_TransactionCustIID = request.getParameter("cust_dropdown_id");
				String Upd_TransactionItemID = request.getParameter("Edit_getItemsDropDown"); 
				String Upd_TransactionBillId = request.getParameter("Edit_Trans_BillId");
				
				System.out.println("UPDATE-->"+Upd_TransactionDate+"~~"+Upd_TransactionAmount+"~~"+Upd_TransactionDescription+"~~"+Upd_TransactionCustIID+"~~"+Upd_TransactionItemID+"~~"+Upd_TransactionBillId);
				
				
				UpdateTransactionMap.put("Upd_TransactionDate_key", Upd_TransactionDate);
				UpdateTransactionMap.put("Upd_TransactionAmount_key", Upd_TransactionAmount);
				UpdateTransactionMap.put("Upd_TransactionDescription_key", Upd_TransactionDescription);
				UpdateTransactionMap.put("Upd_TransactionCustIID_key", Upd_TransactionCustIID);
				UpdateTransactionMap.put("Upd_TransactionItemID_key", Upd_TransactionItemID);
				UpdateTransactionMap.put("Upd_TransactionBillId_key", Upd_TransactionBillId);
				
				String UpdateTransactionStatus = TransactionDAO.updateCreditToTransaction(UpdateTransactionMap);
				System.out.println("FINALLLLLLLLLLL UPDATE STATUS ---------->"+UpdateTransactionStatus);
				if(UpdateTransactionStatus.equalsIgnoreCase("Success")){
					request.setAttribute("returnFlag_UpdateCRTransaction", UpdateTransactionStatus);
					request.getRequestDispatcher("/JSP_Pages/Transaction_CreditTo.jsp").forward(request,response);	
					//response.sendRedirect(request.getContextPath()+"/JSP_Pages/Transaction_CreditTo.jsp?returnFlag_UpdateCRTransaction="+UpdateTransactionStatus);
				}
				else{
					request.setAttribute("returnFlag_UpdateCRTransaction", UpdateTransactionStatus);
					request.getRequestDispatcher("/JSP_Pages/Transaction_CreditTo.jsp").forward(request,response);	
					}
			}
			
			/*For Transaction of DEBIT*/
			
			else if(event.equalsIgnoreCase("DEBITFROM_CURRENTDAY")){
				String DateToPass = request.getParameter("datePassed");
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				transactionCreditBuffer.append(TransactionDAO.ListDebitTransaction(DateToPass));
				out.println(transactionCreditBuffer.toString());			
			}
			
			else if(event.equalsIgnoreCase("ADDNEWDEBITFROMTRANSACTION")){
				String TransactionDate = request.getParameter("Trans_date");
				String TransactionAmount = request.getParameter("Trans_Amount");
				String TransactionDescription = request.getParameter("Trans_Description");
				String TransactionCustIID = request.getParameter("getCustomerDropDown");
				
				
				System.out.println("INSERT-->"+TransactionDate+"~~"+TransactionAmount+"~~"+TransactionDescription+"~~"+TransactionCustIID);
				
				AddNewTransactionMap.put("TransactionDate_key", TransactionDate);
				AddNewTransactionMap.put("TransactionAmount_key", TransactionAmount);
				AddNewTransactionMap.put("TransactionDescription_key", TransactionDescription);
				AddNewTransactionMap.put("TransactionCustIID_key", TransactionCustIID);
				String AddTransactionStatus = TransactionDAO.addNewDebitFromTransaction(AddNewTransactionMap);
				if(AddTransactionStatus.equalsIgnoreCase("success")){
					request.setAttribute("returnFlag_AddNewDRTransaction", AddTransactionStatus);
					request.getRequestDispatcher("/JSP_Pages/Transaction_DebitFrom.jsp").forward(request,response);			
				}
				else{
					request.setAttribute("returnFlag_AddNewDRTransaction", AddTransactionStatus);
					request.getRequestDispatcher("/JSP_Pages/Transaction_DebitFrom.jsp").forward(request,response);	
					}
			}
			
			else if(event.equalsIgnoreCase("EDITDEBITFROMTRANSACTION")){
				String Upd_TransactionDate = request.getParameter("Edit_Trans_date");
				String Upd_TransactionAmount = request.getParameter("Edit_Trans_Amount");
				String Upd_TransactionDescription = request.getParameter("Edit_Trans_Description");
				String Upd_TransactionCustIID = request.getParameter("cust_dropdown_id");
				String Upd_TransactionBillId = request.getParameter("Edit_Trans_BillId");
				
				System.out.println("UPDATE-->"+Upd_TransactionDate+"~~"+Upd_TransactionAmount+"~~"+Upd_TransactionDescription+"~~"+Upd_TransactionCustIID+"~~"+Upd_TransactionBillId);
				
				
				UpdateTransactionMap.put("Upd_TransactionDate_key", Upd_TransactionDate);
				UpdateTransactionMap.put("Upd_TransactionAmount_key", Upd_TransactionAmount);
				UpdateTransactionMap.put("Upd_TransactionDescription_key", Upd_TransactionDescription);
				UpdateTransactionMap.put("Upd_TransactionCustIID_key", Upd_TransactionCustIID);
				UpdateTransactionMap.put("Upd_TransactionBillId_key", Upd_TransactionBillId);
				
				String UpdateTransactionStatus = TransactionDAO.updateDebitFromTransaction(UpdateTransactionMap);
				System.out.println("FINALLLLLLLLLLL UPDATE STATUS ---------->"+UpdateTransactionStatus);
				if(UpdateTransactionStatus.equalsIgnoreCase("Success")){
					request.setAttribute("returnFlag_UpdateDRTransaction", UpdateTransactionStatus);
					request.getRequestDispatcher("/JSP_Pages/Transaction_DebitFrom.jsp").forward(request,response);	
					//response.sendRedirect(request.getContextPath()+"/JSP_Pages/Transaction_CreditTo.jsp?returnFlag_UpdateCRTransaction="+UpdateTransactionStatus);
				}
				else{
					request.setAttribute("returnFlag_UpdateDRTransaction", UpdateTransactionStatus);
					request.getRequestDispatcher("/JSP_Pages/Transaction_DebitFrom.jsp").forward(request,response);	
					}
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
	}

}
