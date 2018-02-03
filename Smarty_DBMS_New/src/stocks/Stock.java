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
		HashMap UpdateStockMap = new HashMap();
		HashMap AddNewStocSaleskMap = new HashMap();
		HashMap UpdateStockSalesMap = new HashMap();
		StockDAO StockDAO = new StockDAO();
		String stockType ="";
	
		try{
			if(event.equalsIgnoreCase("STOCK_CURRENTDAY")){
				
				String DateToPass = request.getParameter("datePassed");
				stockType = request.getParameter("screentype");
				System.out.println("stockType-11111111111-->"+stockType);
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				StockInBuffer.append(StockDAO.ListStockDetails(DateToPass,stockType));		
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
				
				UpdateStockMap.put("Edit_StockDate_key", Edit_StockDate);
				UpdateStockMap.put("Edit_StockAmount_key", Edit_StockAmount);
				UpdateStockMap.put("Edit_StockDescription_key", Edit_StockDescription);
				UpdateStockMap.put("Edit_StockItemID_key", Edit_StockItemIID);
				UpdateStockMap.put("Edit_StockQty_key", Edit_StockQty);
				UpdateStockMap.put("Edit_StockId_key", Edit_StockId);
				String UpdateStockStatus = StockDAO.updateStockDtls(UpdateStockMap);
				if(UpdateStockStatus.equalsIgnoreCase("success")){
					request.setAttribute("returnFlag_UpdateStockIn", UpdateStockStatus);
					request.getRequestDispatcher("/JSP_Pages/StockIn.jsp").forward(request,response);			
				}
				else{
					request.setAttribute("returnFlag_UpdateStockIn", UpdateStockStatus);
					request.getRequestDispatcher("/JSP_Pages/StockIn.jsp").forward(request,response);	
					}
			}
			else if(event.equalsIgnoreCase("ADDNEWSTOCKSALESDTLS")){				
				String StockoutDate = request.getParameter("Add_Stockout_date");
				String StockoutAmount = request.getParameter("Stockout_Amt");
				String StockoutDescription = request.getParameter("Stockout_Description");
				String StockoutItemIID = request.getParameter("getItemsDropDown");
				String StockoutQty = request.getParameter("Stockout_Qty");	

				AddNewStocSaleskMap.put("StockoutDate_key", StockoutDate);
				AddNewStocSaleskMap.put("StockoutAmount_key", StockoutAmount);
				AddNewStocSaleskMap.put("StockoutDescription_key", StockoutDescription);
				AddNewStocSaleskMap.put("StockoutItemID_key", StockoutItemIID);
				AddNewStocSaleskMap.put("StockoutQty_key", StockoutQty);
				String AddStockOutStatus = StockDAO.addNewStockSalesDtls(AddNewStocSaleskMap);
				if(AddStockOutStatus.equalsIgnoreCase("success")){
					request.setAttribute("returnFlag_AddNewStockOut", AddStockOutStatus);
					request.getRequestDispatcher("/JSP_Pages/StockOut.jsp").forward(request,response);			
				}
				else{
					request.setAttribute("returnFlag_AddNewStockOut", AddStockOutStatus);
					request.getRequestDispatcher("/JSP_Pages/StockOut.jsp").forward(request,response);	
					}
			}
			else if(event.equalsIgnoreCase("UPDATESTOCKSALESDTLS")){
				
				String Edit_StockOutDate = request.getParameter("Edit_StockOut_date");
				String Edit_StockOutAmount = request.getParameter("Edit_StockOut_Amount");
				String Edit_StockOutDescription = request.getParameter("Edit_StockOut_Description");
				String Edit_StockOutItemIID = request.getParameter("Edit_getItemsDropDown");
				String Edit_StockOutQty = request.getParameter("Edit_StockOut_Qty");
				String Edit_StockOutId = request.getParameter("Edit_Trans_stockOutid");
				
				UpdateStockSalesMap.put("Edit_StocOutkDate_key", Edit_StockOutDate);
				UpdateStockSalesMap.put("Edit_StockOutAmount_key", Edit_StockOutAmount);
				UpdateStockSalesMap.put("Edit_StockOutDescription_key", Edit_StockOutDescription);
				UpdateStockSalesMap.put("Edit_StockOutItemID_key", Edit_StockOutItemIID);
				UpdateStockSalesMap.put("Edit_StockOutQty_key", Edit_StockOutQty);
				UpdateStockSalesMap.put("Edit_StockOutId_key", Edit_StockOutId);
				String UpdateStockSalesStatus = StockDAO.updateStocSaleskDtls(UpdateStockSalesMap);
				if(UpdateStockSalesStatus.equalsIgnoreCase("success")){
					request.setAttribute("returnFlag_UpdateStockOut", UpdateStockSalesStatus);
					request.getRequestDispatcher("/JSP_Pages/StockOut.jsp").forward(request,response);			
				}
				else{
					request.setAttribute("returnFlag_UpdateStockOut", UpdateStockSalesStatus);
					request.getRequestDispatcher("/JSP_Pages/StockOut.jsp").forward(request,response);	
					}
			}		
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
