package stocks;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Servlet implementation class AddStockIn
 */
@WebServlet("/Stock")
public class Stock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stock() {
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
		String event =request.getParameter("Event");
		StringBuffer StockInBuffer = new StringBuffer();	
		HashMap AddNewStockMap = new HashMap();
		StockDAO StockDAO = new StockDAO();
	
		try{
			if(event.equalsIgnoreCase("STOCK_CURRENTDAY")){
				String DateToPass = request.getParameter("datePassed");
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				StockInBuffer.append(StockDAO.ListStockDetails(DateToPass));		
				out.println(StockInBuffer.toString());
			}
			else if(event.equalsIgnoreCase("ADDNEWSTOCKDTLS")){
				
				String StockDate = request.getParameter("Add_Stock_date");
				String StockAmount = request.getParameter("Stock_Amt");
				String StockDescription = request.getParameter("Stock_Description");
				String StockItemIID = request.getParameter("getItemsDropDown");
				String StockQty = request.getParameter("Stock_Qty");
				
				
				
				AddNewStockMap.put("StockDate_key", StockDate);
				AddNewStockMap.put("StockAmount_key", StockAmount);
				AddNewStockMap.put("StockDescription_key", StockDescription);
				AddNewStockMap.put("StockItemID_key", StockItemIID);
				AddNewStockMap.put("StockQty_key", StockQty);
				String AddStockStatus = StockDAO.addNewStockDtls(AddNewStockMap);
				if(AddStockStatus.equalsIgnoreCase("success")){
					request.setAttribute("returnFlag_AddNewStockIn", AddStockStatus);
					request.getRequestDispatcher("/JSP_Pages/StockIn.jsp").forward(request,response);			
				}
				else{
					request.setAttribute("returnFlag_AddNewStockIn", AddStockStatus);
					request.getRequestDispatcher("/JSP_Pages/StockIn.jsp").forward(request,response);	
					}
			}
			else if(event.equalsIgnoreCase("UPDATESTOCKDTLS")){
				
				String Edit_StockDate = request.getParameter("Edit_Stock_date");
				String Edit_StockAmount = request.getParameter("Edit_Stock_Amount");
				String Edit_StockDescription = request.getParameter("Edit_Stock_Description");
				String Edit_StockItemIID = request.getParameter("Edit_getItemsDropDown");
				String Edit_StockQty = request.getParameter("Edit_Stock_Qty");
				String Edit_StockId = request.getParameter("Edit_Trans_stockid");
				
				
				
				AddNewStockMap.put("Edit_StockDate_key", Edit_StockDate);
				AddNewStockMap.put("Edit_StockAmount_key", Edit_StockAmount);
				AddNewStockMap.put("Edit_StockDescription_key", Edit_StockDescription);
				AddNewStockMap.put("Edit_StockItemID_key", Edit_StockItemIID);
				AddNewStockMap.put("Edit_StockQty_key", Edit_StockQty);
				AddNewStockMap.put("Edit_StockId_key", Edit_StockId);
				String AddStockStatus = StockDAO.addNewStockDtls(AddNewStockMap);
				if(AddStockStatus.equalsIgnoreCase("success")){
					request.setAttribute("returnFlag_AddNewStockIn", AddStockStatus);
					request.getRequestDispatcher("/JSP_Pages/StockIn.jsp").forward(request,response);			
				}
				else{
					request.setAttribute("returnFlag_AddNewStockIn", AddStockStatus);
					request.getRequestDispatcher("/JSP_Pages/StockIn.jsp").forward(request,response);	
					}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
