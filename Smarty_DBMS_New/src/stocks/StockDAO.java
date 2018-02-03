package stocks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import dbConnection.DbConnection;

public class StockDAO {

	public String ListStockDetails(String GivenDate, String StockType){
		System.out.println("INTO DAO CLASSS");
		System.out.println("GivenDate-------->"+GivenDate);
		if(StockType.equalsIgnoreCase("STOCKIN")){
			StringBuffer Unit_Buffer = null;
			Connection conn = null;

			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			String currentDate = sdf.format(dt);
			System.out.println("currentDate--->"+currentDate);
			String query = "select Stock_Id,Stock_Date,Item_Name,Stock_Type,Stock_Qty,Stock_Amount,Param_1 from stock_info where Stock_Type='STOCK_IN' and Stock_Date = ?";
			ResultSet rSet = null;
			int i = 0;
			PreparedStatement psmt = null;
			try {
				Unit_Buffer = new StringBuffer();
				conn = DbConnection.getConnection();
				psmt = conn.prepareStatement(query);
				psmt.setString(1, GivenDate);
				System.out.println("Query for for Stock In-ssss-->" + psmt);
				rSet = psmt.executeQuery();
				while (rSet.next()) {
					Unit_Buffer.append(rSet.getString("Stock_Id"));
					Unit_Buffer.append("~");
					Unit_Buffer.append(rSet.getString("Stock_Date"));
					Unit_Buffer.append("~");
					Unit_Buffer.append(rSet.getString("Item_Name"));
					Unit_Buffer.append("~");
					Unit_Buffer.append(rSet.getString("Stock_Qty"));
					Unit_Buffer.append("~");
					Unit_Buffer.append(rSet.getString("Stock_Amount"));
					Unit_Buffer.append("~");
					Unit_Buffer.append(rSet.getString("Stock_Type"));
					Unit_Buffer.append("~");			
					Unit_Buffer.append(nullCheck(rSet.getString("Param_1")));
					Unit_Buffer.append("#");
				}
			} catch (Exception e) {
				System.out.println("Something went wrong during Unit........");
				e.printStackTrace();

			} finally {

				try {
					if (rSet != null) {
						rSet.close();
					}
					if (psmt != null) {
						psmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			return Unit_Buffer.toString();	
		}
		else if(StockType.equalsIgnoreCase("STOCKOUT")){
			StringBuffer Unit_Buffer = null;
			Connection conn = null;

			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			String currentDate = sdf.format(dt);
			System.out.println("currentDate--->"+currentDate);
			String query = "select Stock_Id,Stock_Date,Item_Name,Stock_Type,Stock_Qty,Stock_Amount,Param_1 from stock_info where Stock_Type='STOCK_OUT' and Stock_Date = ?";
			ResultSet rSet = null;
			int i = 0;
			PreparedStatement psmt = null;
			try {
				Unit_Buffer = new StringBuffer();
				conn = DbConnection.getConnection();
				psmt = conn.prepareStatement(query);
				psmt.setString(1, GivenDate);
				System.out.println("Query for for Stock In-ssss-->" + psmt);
				rSet = psmt.executeQuery();
				while (rSet.next()) {
					Unit_Buffer.append(rSet.getString("Stock_Id"));
					Unit_Buffer.append("~");
					Unit_Buffer.append(rSet.getString("Stock_Date"));
					Unit_Buffer.append("~");
					Unit_Buffer.append(rSet.getString("Item_Name"));
					Unit_Buffer.append("~");
					Unit_Buffer.append(rSet.getString("Stock_Qty"));
					Unit_Buffer.append("~");
					Unit_Buffer.append(rSet.getString("Stock_Amount"));
					Unit_Buffer.append("~");
					Unit_Buffer.append(rSet.getString("Stock_Type"));
					Unit_Buffer.append("~");			
					Unit_Buffer.append(nullCheck(rSet.getString("Param_1")));
					Unit_Buffer.append("#");
				}
			} catch (Exception e) {
				System.out.println("Something went wrong during Unit........");
				e.printStackTrace();

			} finally {

				try {
					if (rSet != null) {
						rSet.close();
					}
					if (psmt != null) {
						psmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			return Unit_Buffer.toString();	
		}
		else{
			return "Error Occuerred Please try again later";
		}
		
		
		
	}
	
	
	public String addNewStockDtls(HashMap<String,String> AddnewStockMapReq){

		System.out.println("INTO DAO CLASSS::addNewStockDtls(NEWWWWWWWWWWWWWW)");
		Connection conn =null;
		ResultSet rSet1 = null;
		ResultSet rSet2 = null;
		ResultSet rSet3 = null;
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;	
		PreparedStatement psmt3 = null;	
		String InsertNewStockDtlsStatus = "Failed";
		String ItemName = "";
;		try {
				int insertedRows = 0;
				conn = DbConnection.getConnection();
				
				
				String getItemName = "select Value from maintenance_master where Unique_ID = ?";
				psmt2 = conn.prepareStatement(getItemName);
				psmt2.setString(1,AddnewStockMapReq.get("StockItemID_key") );
				rSet2 = psmt2.executeQuery();
				while(rSet2.next()){ItemName = rSet2.getString("Value");}
				
				
				String InsertNewCreditToRecord = "INSERT INTO stock_info(Stock_Date,Item_Id,Item_Name,Stock_Type,Stock_Qty,Stock_Amount,Param_1,Created_dt) values (?,?,?,'STOCK_IN',?,?,?,SYSDATE())";
				psmt3 = conn.prepareStatement(InsertNewCreditToRecord);
				psmt3.setString(1, AddnewStockMapReq.get("StockDate_key"));
				psmt3.setString(2, AddnewStockMapReq.get("StockItemID_key"));
				psmt3.setString(3, ItemName);
				psmt3.setString(4, AddnewStockMapReq.get("StockQty_key"));
				psmt3.setString(5, AddnewStockMapReq.get("StockAmount_key"));
				psmt3.setString(6, AddnewStockMapReq.get("StockDescription_key"));
				insertedRows = psmt3.executeUpdate();
				
				if(insertedRows > 0){
					InsertNewStockDtlsStatus = "Success";
					
				}
				else{
					InsertNewStockDtlsStatus = "Failed";
				}	
		} catch (Exception e) {
			System.out.println("Something went wrong during Unit........");
			e.printStackTrace();

		} finally {

			try {
				if (rSet1 != null) {rSet1.close();}
				if (rSet2 != null) {rSet2.close();}
				if (rSet3 != null) {rSet3.close();}
				if (psmt1 != null) {psmt1.close();}
				if (psmt2 != null) {psmt2.close();}
				if (psmt3 != null) {psmt3.close();}
				if (conn != null) {conn.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return InsertNewStockDtlsStatus;
	}
	
	
	public String addNewStockSalesDtls(HashMap<String,String> AddNewStocSaleskMapReq){

		System.out.println("INTO DAO CLASSS::addNewStockSalesDtls(NEWWWWWWWWWWWWWW)");
		Connection conn =null;
		ResultSet rSet1 = null;
		ResultSet rSet2 = null;
		ResultSet rSet3 = null;
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;	
		PreparedStatement psmt3 = null;	
		String InsertNewStockSalesDtlsStatus = "Failed";
		String ItemName = "";
;		try {
				int insertedRows = 0;
				conn = DbConnection.getConnection();
				
				
				String getItemName = "select Value from maintenance_master where Unique_ID = ?";
				psmt2 = conn.prepareStatement(getItemName);
				psmt2.setString(1,AddNewStocSaleskMapReq.get("StockoutItemID_key") );
				rSet2 = psmt2.executeQuery();
				while(rSet2.next()){ItemName = rSet2.getString("Value");}
				
				
				String InsertNewCreditToRecord = "INSERT INTO stock_info(Stock_Date,Item_Id,Item_Name,Stock_Type,Stock_Qty,Stock_Amount,Param_1,Created_dt) values (?,?,?,'STOCK_OUT',?,?,?,SYSDATE())";
				psmt3 = conn.prepareStatement(InsertNewCreditToRecord);
				psmt3.setString(1, AddNewStocSaleskMapReq.get("StockoutDate_key"));
				psmt3.setString(2, AddNewStocSaleskMapReq.get("StockoutItemID_key"));
				psmt3.setString(3, ItemName);
				psmt3.setString(4, AddNewStocSaleskMapReq.get("StockoutQty_key"));
				psmt3.setString(5, AddNewStocSaleskMapReq.get("StockoutAmount_key"));
				psmt3.setString(6, AddNewStocSaleskMapReq.get("StockoutDescription_key"));
				insertedRows = psmt3.executeUpdate();
				
				if(insertedRows > 0){
					InsertNewStockSalesDtlsStatus = "Success";
					
				}
				else{
					InsertNewStockSalesDtlsStatus = "Failed";
				}	
		} catch (Exception e) {
			System.out.println("Something went wrong during Unit........");
			e.printStackTrace();

		} finally {

			try {
				if (rSet1 != null) {rSet1.close();}
				if (rSet2 != null) {rSet2.close();}
				if (rSet3 != null) {rSet3.close();}
				if (psmt1 != null) {psmt1.close();}
				if (psmt2 != null) {psmt2.close();}
				if (psmt3 != null) {psmt3.close();}
				if (conn != null) {conn.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return InsertNewStockSalesDtlsStatus;
	}
	
	
	
	public String updateStockDtls(HashMap<String,String> UpdateStockMapReq){

		System.out.println("INTO DAO CLASSS::updateStockDtls()");
		Connection conn =null;
		ResultSet rSet1 = null;
		ResultSet rSet2 = null;
		ResultSet rSet3 = null;
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;	
		PreparedStatement psmt3 = null;	
		String UpdateStockDtlsStatus = "Failed";
		String ItemName = "";
;		try {
				int insertedRows = 0;
				conn = DbConnection.getConnection();
				
				
				String getItemName = "select Value from maintenance_master where Unique_ID = ?";
				psmt2 = conn.prepareStatement(getItemName);
				psmt2.setString(1,UpdateStockMapReq.get("Edit_StockItemID_key") );
				rSet2 = psmt2.executeQuery();
				while(rSet2.next()){ItemName = rSet2.getString("Value");}
				
				
				String UpdateStockInRecord = "UPDATE stock_info SET Stock_Date = ?,Item_Id=?,Item_Name = ?,Stock_Qty = ?,Stock_Amount =? ,Param_1 = ?,Modified_dt = SYSDATE() WHERE Stock_Id= ?";
				psmt3 = conn.prepareStatement(UpdateStockInRecord);
				psmt3.setString(1, UpdateStockMapReq.get("Edit_StockDate_key"));
				psmt3.setString(2, UpdateStockMapReq.get("Edit_StockItemID_key"));
				psmt3.setString(3, ItemName);
				psmt3.setString(4, UpdateStockMapReq.get("Edit_StockQty_key"));
				psmt3.setString(5, UpdateStockMapReq.get("Edit_StockAmount_key"));
				psmt3.setString(6, UpdateStockMapReq.get("Edit_StockDescription_key"));
				psmt3.setString(7, UpdateStockMapReq.get("Edit_StockId_key"));				
				insertedRows = psmt3.executeUpdate();
				
				if(insertedRows > 0){
					UpdateStockDtlsStatus = "Success";
					
				}
				else{
					UpdateStockDtlsStatus = "Failed";
				}	
		} catch (Exception e) {
			System.out.println("Something went wrong during Unit........");
			e.printStackTrace();

		} finally {

			try {
				if (rSet1 != null) {rSet1.close();}
				if (rSet2 != null) {rSet2.close();}
				if (rSet3 != null) {rSet3.close();}
				if (psmt1 != null) {psmt1.close();}
				if (psmt2 != null) {psmt2.close();}
				if (psmt3 != null) {psmt3.close();}
				if (conn != null) {conn.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return UpdateStockDtlsStatus;
	}
	
	
	public String updateStocSaleskDtls(HashMap<String,String> UpdateStocSaleskMapReq){

		System.out.println("INTO DAO CLASSS::updateStocSaleskDtls()");
		Connection conn =null;
		ResultSet rSet1 = null;
		ResultSet rSet2 = null;
		ResultSet rSet3 = null;
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;	
		PreparedStatement psmt3 = null;	
		String UpdateStockSalesDtlsStatus = "Failed";
		String ItemName = "";
;		try {
				int insertedRows = 0;
				conn = DbConnection.getConnection();
				
				
				String getItemName = "select Value from maintenance_master where Unique_ID = ?";
				psmt2 = conn.prepareStatement(getItemName);
				psmt2.setString(1,UpdateStocSaleskMapReq.get("Edit_StockOutItemID_key") );
				rSet2 = psmt2.executeQuery();
				while(rSet2.next()){ItemName = rSet2.getString("Value");}
				
				
				String UpdateStockInRecord = "UPDATE stock_info SET Stock_Date = ?,Item_Id=?,Item_Name = ?,Stock_Qty = ?,Stock_Amount =? ,Param_1 = ?,Modified_dt = SYSDATE() WHERE Stock_Id= ?";
				psmt3 = conn.prepareStatement(UpdateStockInRecord);
				psmt3.setString(1, UpdateStocSaleskMapReq.get("Edit_StocOutkDate_key"));
				psmt3.setString(2, UpdateStocSaleskMapReq.get("Edit_StockOutItemID_key"));
				psmt3.setString(3, ItemName);
				psmt3.setString(4, UpdateStocSaleskMapReq.get("Edit_StockOutQty_key"));
				psmt3.setString(5, UpdateStocSaleskMapReq.get("Edit_StockOutAmount_key"));
				psmt3.setString(6, UpdateStocSaleskMapReq.get("Edit_StockOutDescription_key"));
				psmt3.setString(7, UpdateStocSaleskMapReq.get("Edit_StockOutId_key"));				
				insertedRows = psmt3.executeUpdate();
				
				if(insertedRows > 0){
					UpdateStockSalesDtlsStatus = "Success";
					
				}
				else{
					UpdateStockSalesDtlsStatus = "Failed";
				}	
		} catch (Exception e) {
			System.out.println("Something went wrong during Unit........");
			e.printStackTrace();

		} finally {

			try {
				if (rSet1 != null) {rSet1.close();}
				if (rSet2 != null) {rSet2.close();}
				if (rSet3 != null) {rSet3.close();}
				if (psmt1 != null) {psmt1.close();}
				if (psmt2 != null) {psmt2.close();}
				if (psmt3 != null) {psmt3.close();}
				if (conn != null) {conn.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return UpdateStockSalesDtlsStatus;
	}
	
	
	
	
	
	public String nullCheck(String givenText) {
		String FinalString = "";

		if (givenText == null || givenText.trim() == "") {
			FinalString = "-";
		} else {
			FinalString = givenText;

		}
		return FinalString;
	}
}
