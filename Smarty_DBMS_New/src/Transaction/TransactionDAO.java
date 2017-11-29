package Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;

import dbConnection.DbConnection;

public class TransactionDAO {

	public String ListTransaction(String GivenDate) {
		System.out.println("INTO DAO CLASSS");
		System.out.println("GivenDate-------->"+GivenDate);
		
		StringBuffer Unit_Buffer = null;
		Connection conn = null;

		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sdf.format(dt);
		System.out.println("currentDate--->"+currentDate);
		String query = "select Bill_id,Cust_Name,Bill_Amt,Bill_Type,Description from cust_bill_info where Bill_Type='CR' and Bill_Date = ?";
		ResultSet rSet = null;
		int i = 0;
		PreparedStatement psmt = null;
		try {
			Unit_Buffer = new StringBuffer();
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, GivenDate);
			System.out.println("Query for for TransactionCredit-ssss-->" + psmt);
			rSet = psmt.executeQuery();
			while (rSet.next()) {
				Unit_Buffer.append(rSet.getString("Bill_id"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Cust_Name"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Bill_Amt"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Bill_Type"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(nullCheck(rSet.getString("Description")));
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
		System.out.println("sdlkfjsdlkfjklsdjfkldsjklfjdsklj---->" + Unit_Buffer.toString());
		return Unit_Buffer.toString();
	}
	
	public String getCustomers(){

		System.out.println("INTO DAO CLASSS::getCustomers()");
		StringBuffer Unit_Buffer = null;
		Connection conn = null;

		String query = "select Cust_ID,Cust_Name from customer_info where Cust_Status = 'ACTIVE'";
		ResultSet rSet = null;
		int i = 0;
		PreparedStatement psmt = null;
		try {
			Unit_Buffer = new StringBuffer();
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(query);
			System.out.println("Query for for TransactionCredit-ssss-->" + psmt);
			rSet = psmt.executeQuery();
			while (rSet.next()) {
				Unit_Buffer.append(rSet.getString("Cust_ID"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Cust_Name"));
				Unit_Buffer.append("#");
			}
			System.out.println("Unit_Buffer---getCustomers()---->"+Unit_Buffer);
		} catch (Exception e) {
			System.out.println("Something went wrong during Unit::getCustomers()");
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
	
	
	public String getItems(){

		System.out.println("INTO DAO CLASSS :: getItems()");
		StringBuffer Unit_Buffer = null;
		Connection conn = null;

		String query = "select Unique_ID,Value from maintenance_master where Status = 'ACTIVE' and Param_1 = 'ITEM_TYPE'";
		ResultSet rSet = null;
		int i = 0;
		PreparedStatement psmt = null;
		try {
			Unit_Buffer = new StringBuffer();
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(query);
			System.out.println("Query for for TransactionCredit-ssss-->" + psmt);
			rSet = psmt.executeQuery();
			while (rSet.next()) {
				Unit_Buffer.append(rSet.getString("Unique_ID"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Value"));
				Unit_Buffer.append("#");
			}
			System.out.println("Unit_Buffer---getItems()---->"+Unit_Buffer);
		} catch (Exception e) {
			System.out.println("Something went wrong during Unit  :: getItems()");
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

	
	public String addNewCreditToTransaction(HashMap<String,String> AddTransactionMapReq){

		System.out.println("INTO DAO CLASSS::addNewTransaction()");
		Connection conn =null;
		ResultSet rSet1 = null;
		ResultSet rSet2 = null;
		ResultSet rSet3 = null;
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;	
		PreparedStatement psmt3 = null;	
		String InsertNewCreditToTransaction = "Failed";
		String CustomerName = "";
		String ItemName = "";
		try {
				int insertedRows = 0;
				conn = DbConnection.getConnection();
				String getCustName = "select Cust_Name from customer_info where Cust_ID = ?";			
				psmt1 = conn.prepareStatement(getCustName);
				psmt1.setString(1,AddTransactionMapReq.get("TransactionCustIID_key"));
				rSet1 = psmt1.executeQuery();
				while(rSet1.next()){CustomerName = rSet1.getString("Cust_Name");}
				
				
				String getItemName = "select Value from maintenance_master where Unique_ID = ?";
				psmt2 = conn.prepareStatement(getItemName);
				psmt2.setString(1,AddTransactionMapReq.get("TransactionItemID_key") );
				rSet2 = psmt2.executeQuery();
				while(rSet2.next()){ItemName = rSet2.getString("Value");}
				
				
				String InsertNewCreditToRecord = "INSERT INTO cust_bill_info(Bill_Date,Cust_ID,Cust_Name,Bill_Type,Bill_Amt,Created_dt,Modified_Dt,Bill_Item_Id,Bill_Item_Name,Description) values (?,?,?,?,?,SYSDATE(),null,?,?,?)";
				psmt3 = conn.prepareStatement(InsertNewCreditToRecord);
				psmt3.setString(1, AddTransactionMapReq.get("TransactionDate_key"));
				psmt3.setString(2, AddTransactionMapReq.get("TransactionCustIID_key"));
				psmt3.setString(3, CustomerName);
				psmt3.setString(4, "CR");
				psmt3.setString(5, AddTransactionMapReq.get("TransactionAmount_key"));
				psmt3.setString(6, AddTransactionMapReq.get("TransactionItemID_key"));
				psmt3.setString(7, ItemName);
				psmt3.setString(8, AddTransactionMapReq.get("TransactionDescription_key"));
				insertedRows = psmt3.executeUpdate();
				
				if(insertedRows > 0){
					InsertNewCreditToTransaction = "Success";
				}
				else{
					InsertNewCreditToTransaction = "Failed";
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
		return InsertNewCreditToTransaction;
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
