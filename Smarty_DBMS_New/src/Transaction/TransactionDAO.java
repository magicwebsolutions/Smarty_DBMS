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
		String query = "select Bill_id,Cust_ID,Cust_Name,Bill_Amt,Bill_Type,Description,Bill_date,Bill_Item_Name from cust_bill_info where Bill_Type='CR' and Bill_Date = ?";
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
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getDate("Bill_date"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Bill_Item_Name"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Cust_ID"));
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
		ResultSet rSet4 = null;
		ResultSet rSet5 = null;
		ResultSet rSet6 = null;
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;	
		PreparedStatement psmt3 = null;	
		PreparedStatement psmt4 = null;	
		PreparedStatement psmt5 = null;	
		PreparedStatement psmt6 = null;	
		PreparedStatement psmt7 = null;	
		String InsertNewCreditToTransaction = "Failed";
		String CustomerName = "";
		String ItemName = "";
		int Curr_CR_AMt = 0;
		int Curr_Outstanding = 0;
		int insertedRow = 0;	
;		try {
				int insertedRows = 0;
				int GeneratedCustomerIDkey= 0;
				int CustomerID = 0;
				conn = DbConnection.getConnection();
				if(AddTransactionMapReq.get("TransactionCustIID_key") != null){
				CustomerID = Integer.parseInt(AddTransactionMapReq.get("TransactionCustIID_key"));}
				
				if(AddTransactionMapReq.get("TransactionCustIID_key") !=null){
					String getCustName = "select Cust_Name from customer_info where Cust_ID = ?";			
					psmt1 = conn.prepareStatement(getCustName);
					psmt1.setString(1,AddTransactionMapReq.get("TransactionCustIID_key"));
					rSet1 = psmt1.executeQuery();
					while(rSet1.next()){
						CustomerName = rSet1.getString("Cust_Name");
						}
				}
				else{
					 
					 System.out.println("asdfsdfsdfsdfsdfasdflnklnwoerunwunfsdf");
					String addNewCustomerQuery = "INSERT INTO customer_info(Cust_Type,Cust_Name,Cust_Phone,Cust_Address,Cust_CR_Amt,Cust_DR_Amt,Cust_Outstanding,Cust_Status,Created_dt,Modified_Dt) values (?,?,?,?,0,0,0,'ACTIVE',SYSDATE(),SYSDATE())";
					psmt6 = conn.prepareStatement(addNewCustomerQuery);
					psmt6.setString(1, AddTransactionMapReq.get("TransactionNewCustType_key"));
					psmt6.setString(2, AddTransactionMapReq.get("TransactionNewCustName_key"));
					psmt6.setString(3, AddTransactionMapReq.get("TransactionNewCustPhone_key"));
					psmt6.setString(4, AddTransactionMapReq.get("TransactionNewCustAddress_key"));
					psmt6.executeUpdate();
					 ResultSet rs = psmt6.getGeneratedKeys();
				        if (rs.next()) {
				        	GeneratedCustomerIDkey = rs.getInt(1);
				        }
				        CustomerID = GeneratedCustomerIDkey;
				        
				        String getCustName_Gen = "select Cust_Name from customer_info where Cust_ID = ?";			
						psmt7 = conn.prepareStatement(getCustName_Gen);
						psmt7.setInt(1,CustomerID);
						rSet6 = psmt7.executeQuery();
						while(rSet6.next()){
							CustomerName = rSet6.getString("Cust_Name");
							}
						
					
					
				}
				
				
				
				String getItemName = "select Value from maintenance_master where Unique_ID = ?";
				psmt2 = conn.prepareStatement(getItemName);
				psmt2.setString(1,AddTransactionMapReq.get("TransactionItemID_key") );
				rSet2 = psmt2.executeQuery();
				while(rSet2.next()){ItemName = rSet2.getString("Value");}
				
				
				String InsertNewCreditToRecord = "INSERT INTO cust_bill_info(Bill_Date,Cust_ID,Cust_Name,Bill_Type,Bill_Amt,Created_dt,Modified_Dt,Bill_Item_Id,Bill_Item_Name,Description) values (?,?,?,?,?,SYSDATE(),SYSDATE(),?,?,?)";
				psmt3 = conn.prepareStatement(InsertNewCreditToRecord);
				psmt3.setString(1, AddTransactionMapReq.get("TransactionDate_key"));
				psmt3.setInt(2, CustomerID);
				psmt3.setString(3, CustomerName);
				psmt3.setString(4, "CR");
				psmt3.setString(5, AddTransactionMapReq.get("TransactionAmount_key"));
				psmt3.setString(6, AddTransactionMapReq.get("TransactionItemID_key"));
				psmt3.setString(7, ItemName);
				psmt3.setString(8, AddTransactionMapReq.get("TransactionDescription_key"));
				insertedRows = psmt3.executeUpdate();
				
				if(insertedRows > 0){
					String curr_CR_Amt = "SELECT Cust_CR_Amt,Cust_Outstanding from customer_info where Cust_ID = ?";
					psmt4 = conn.prepareStatement(curr_CR_Amt);
					psmt4.setString(1,AddTransactionMapReq.get("TransactionCustIID_key"));
					rSet4 = psmt4.executeQuery();
					while(rSet4.next()){
						Curr_CR_AMt = rSet4.getInt("Cust_CR_Amt");	
						Curr_Outstanding = rSet4.getInt("Cust_Outstanding");	
					}
					
					int updt_CR_Amt = Curr_CR_AMt + Integer.parseInt(AddTransactionMapReq.get("TransactionAmount_key"));
					int updt_Outstanding_Amt = Curr_Outstanding + Integer.parseInt(AddTransactionMapReq.get("TransactionAmount_key"));
					
					String MasterCustomerTable = "UPDATE customer_info SET Cust_CR_Amt = ?,Cust_Outstanding = ?,Modified_Dt =SYSDATE()	where Cust_ID = ?";
					psmt5 = conn.prepareStatement(MasterCustomerTable);
					psmt5.setInt(1,updt_CR_Amt);
					psmt5.setInt(2,updt_Outstanding_Amt);
					psmt5.setInt(3,CustomerID);
					insertedRows = psmt5.executeUpdate();
					if(insertedRows > 0){InsertNewCreditToTransaction = "Success";}
					else{InsertNewCreditToTransaction = "Failed";}
					
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
	
	
	public String updateCreditToTransaction(HashMap<String,String> UpdateTransactionMap){
		System.out.println("INTO DAO CLASSS::updateCreditToTransaction()");
		Connection conn =null;
		ResultSet rSet1 = null;
		ResultSet rSet2 = null;
		ResultSet rSet3 = null;
		ResultSet rSet4 = null;
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;	
		PreparedStatement psmt3 = null;	
		PreparedStatement psmt4 = null;	
		PreparedStatement psmt5 = null;
		
		String InsertNewCreditToTransaction = "Failed";
		String CustomerName = "";
		String ItemName = "";
		
		double OldAmount = 0;
		double NewAmount = 0;
		double DiffAmount = 0;
		double finalTotAmt = 0;
		
		double curr_credit_Amt = 0;
		double curr_Outstanding_Amt =0;
		
		
		try {
			
			int transactioncount = 0;
			
			conn = DbConnection.getConnection();
		
			String getItemName = "select Value from maintenance_master where Unique_ID = ?";
			psmt2 = conn.prepareStatement(getItemName);
			psmt2.setString(1,UpdateTransactionMap.get("Upd_TransactionItemID_key") );
			rSet2 = psmt2.executeQuery();
			while(rSet2.next()){ItemName = rSet2.getString("Value");}
			
			String getQuery = "SELECT * from cust_bill_info where Bill_Id = ?";
			psmt1 = conn.prepareStatement(getQuery);
			psmt1.setString(1,UpdateTransactionMap.get("Upd_TransactionBillId_key"));
			rSet1 = psmt1.executeQuery();
			while(rSet1.next()){OldAmount = rSet1.getInt("Bill_Amt");}
			
			NewAmount = Float.valueOf(UpdateTransactionMap.get("Upd_TransactionAmount_key"));
			DiffAmount =  NewAmount - OldAmount;
			
			String infoUpdate = "UPDATE cust_bill_info SET Bill_Date = ?,Bill_Amt= ?, Bill_Item_Id = ?, Bill_Item_Name = ?, Description =?, Modified_Dt = SYSDATE() WHERE Bill_Id = ? ";
System.out.println("HEEheheheeheheheheheeh)))))))))))))))))))))))))))))))))))))0"+infoUpdate);
			psmt3 = conn.prepareStatement(infoUpdate);
			psmt3.setString(1,UpdateTransactionMap.get("Upd_TransactionDate_key"));
			psmt3.setString(2,UpdateTransactionMap.get("Upd_TransactionAmount_key"));
			psmt3.setString(3,UpdateTransactionMap.get("Upd_TransactionItemID_key"));
			psmt3.setString(4,ItemName);
			psmt3.setString(5,UpdateTransactionMap.get("Upd_TransactionDescription_key"));
			psmt3.setString(6,UpdateTransactionMap.get("Upd_TransactionBillId_key"));
			transactioncount = psmt3.executeUpdate();
			
			System.out.println("11111111111111111111111111111111-transactioncount->"+transactioncount);
			if(transactioncount > 0){
				String curr_CR_Amt = "SELECT Cust_CR_Amt,Cust_Outstanding from customer_info where Cust_ID = ?";
				psmt4 = conn.prepareStatement(curr_CR_Amt);
				System.out.println("Upd_TransactionCustIID_key-Upd_TransactionCustIID_key->"+UpdateTransactionMap.get("Upd_TransactionCustIID_key"));
				System.out.println("curr_CR_Amt-curr_CR_Amt->"+curr_CR_Amt);
				psmt4.setString(1,UpdateTransactionMap.get("Upd_TransactionCustIID_key"));
				rSet4 = psmt4.executeQuery();
				while(rSet4.next()){
					curr_credit_Amt = rSet4.getInt("Cust_CR_Amt");	
					curr_Outstanding_Amt = rSet4.getInt("Cust_Outstanding");	
				}
				
				System.out.println("*********************************************************-curr_credit_Amt->"+curr_credit_Amt);
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^-curr_Outstanding_Amt->"+curr_Outstanding_Amt);
				
				double updt_CR_Amt = curr_credit_Amt + DiffAmount;
				double updt_Outstanding_Amt = curr_Outstanding_Amt + DiffAmount;
				
				System.out.println("2222222222222222222222222222222222222222222222222222-updt_CR_Amt->"+updt_CR_Amt);
				System.out.println("3333333333333333333333333333333333333333333333333333-updt_Outstanding_Amt->"+updt_Outstanding_Amt);
				System.out.println("4444444444444444444444444444444444444444-DiffAmount->"+DiffAmount);
				
				String MasterUpdate = "UPDATE customer_info SET Cust_CR_Amt = ?,Cust_Outstanding = ?,Modified_Dt =SYSDATE()	where Cust_ID = ?";
				psmt5 = conn.prepareStatement(MasterUpdate);
				psmt5.setDouble(1, updt_CR_Amt);
				psmt5.setDouble(2, updt_Outstanding_Amt);
				psmt5.setString(3, UpdateTransactionMap.get("Upd_TransactionCustIID_key"));
				transactioncount = psmt5.executeUpdate();
				System.out.println("5555555555555555555555555555555555555-transactioncount->"+transactioncount);
				if(transactioncount > 0){
					System.out.println("666666666666666666666666666666666666666666666666666-->");
					InsertNewCreditToTransaction = "Success";
				}
				else
				{
					InsertNewCreditToTransaction = "Failed";
				}
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
	
	/*FOR DEBIT FROM */
	
	public String ListDebitTransaction(String GivenDate) {
		System.out.println("INTO DAO CLASSS");
		System.out.println("GivenDate-------->"+GivenDate);
		
		StringBuffer Unit_Buffer = null;
		Connection conn = null;

		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sdf.format(dt);
		System.out.println("currentDate--->"+currentDate);
		String query = "select Bill_id,Cust_ID,Cust_Name,Bill_Amt,Bill_Type,Description,Bill_date,Bill_Item_Name from cust_bill_info where Bill_Type='DR' and Bill_Date = ?";
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
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getDate("Bill_date"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Bill_Item_Name"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Cust_ID"));
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
	
	
	
	public String addNewDebitFromTransaction(HashMap<String,String> AddTransactionMapReq){

		System.out.println("INTO DAO CLASSS::addNewDebitFromTransaction()");
		Connection conn =null;
		ResultSet rSet1 = null;
		ResultSet rSet2 = null;
		ResultSet rSet3 = null;
		ResultSet rSet4 = null;
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;	
		PreparedStatement psmt3 = null;	
		PreparedStatement psmt4 = null;	
		PreparedStatement psmt5 = null;	
		//PreparedStatement psmt6 = null;	
		String InsertNewDebitFromTransaction = "Failed";
		String CustomerName = "";
		//String ItemName = "";
		int Curr_DR_AMt = 0;
		int Curr_Outstanding = 0;
;		try {
				int insertedRows = 0;
				conn = DbConnection.getConnection();
				String getCustName = "select Cust_Name from customer_info where Cust_ID = ?";			
				psmt1 = conn.prepareStatement(getCustName);
				psmt1.setString(1,AddTransactionMapReq.get("TransactionCustIID_key"));
				rSet1 = psmt1.executeQuery();
				while(rSet1.next()){CustomerName = rSet1.getString("Cust_Name");}
				
				
				/*String getItemName = "select Value from maintenance_master where Unique_ID = ?";
				psmt2 = conn.prepareStatement(getItemName);
				psmt2.setString(1,AddTransactionMapReq.get("TransactionItemID_key") );
				rSet2 = psmt2.executeQuery();
				while(rSet2.next()){ItemName = rSet2.getString("Value");}*/
				
				
				String InsertNewCreditToRecord = "INSERT INTO cust_bill_info(Bill_Date,Cust_ID,Cust_Name,Bill_Type,Bill_Amt,Created_dt,Modified_Dt,Bill_Item_Id,Bill_Item_Name,Description) values (?,?,?,?,?,SYSDATE(),null,?,?,?)";
				psmt3 = conn.prepareStatement(InsertNewCreditToRecord);
				psmt3.setString(1, AddTransactionMapReq.get("TransactionDate_key"));
				psmt3.setString(2, AddTransactionMapReq.get("TransactionCustIID_key"));
				psmt3.setString(3, CustomerName);
				psmt3.setString(4, "DR");
				psmt3.setString(5, AddTransactionMapReq.get("TransactionAmount_key"));
				psmt3.setString(6, null);
				psmt3.setString(7, null);
				psmt3.setString(8, AddTransactionMapReq.get("TransactionDescription_key"));
				insertedRows = psmt3.executeUpdate();
				
				if(insertedRows > 0){
					String curr_DR_Amt = "SELECT Cust_DR_Amt,Cust_Outstanding from customer_info where Cust_ID = ?";
					psmt4 = conn.prepareStatement(curr_DR_Amt);
					psmt4.setString(1,AddTransactionMapReq.get("TransactionCustIID_key"));
					rSet4 = psmt4.executeQuery();
					while(rSet4.next()){
						Curr_DR_AMt = rSet4.getInt("Cust_DR_Amt");	
						Curr_Outstanding = rSet4.getInt("Cust_Outstanding");	
					}
					
					int updt_DR_Amt = Curr_DR_AMt + Integer.parseInt(AddTransactionMapReq.get("TransactionAmount_key"));
					int updt_Outstanding_Amt = Curr_Outstanding - Integer.parseInt(AddTransactionMapReq.get("TransactionAmount_key"));
					
					String MasterCustomerTable = "UPDATE customer_info SET Cust_DR_Amt = ?,Cust_Outstanding = ?,Modified_Dt =SYSDATE()	where Cust_ID = ?";
					psmt5 = conn.prepareStatement(MasterCustomerTable);
					psmt5.setInt(1,updt_DR_Amt);
					psmt5.setInt(2,updt_Outstanding_Amt);
					psmt5.setString(3,AddTransactionMapReq.get("TransactionCustIID_key"));
					insertedRows = psmt5.executeUpdate();
					if(insertedRows > 0){InsertNewDebitFromTransaction = "Success";}
					else{InsertNewDebitFromTransaction = "Failed";}
					
				}
				else{
					InsertNewDebitFromTransaction = "Failed";
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
		return InsertNewDebitFromTransaction;
	}
	
	
	public String updateDebitFromTransaction(HashMap<String,String> UpdateTransactionMap){
		System.out.println("INTO DAO CLASSS::updateDebitFromTransaction()");
		Connection conn =null;
		ResultSet rSet1 = null;
		ResultSet rSet2 = null;
		ResultSet rSet3 = null;
		ResultSet rSet4 = null;
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;	
		PreparedStatement psmt3 = null;	
		PreparedStatement psmt4 = null;	
		PreparedStatement psmt5 = null;
		
		String InsertNewDebitTransaction = "Failed";
		String CustomerName = "";	
		
		double OldAmount = 0;
		double NewAmount = 0;
		double DiffAmount = 0;
		double finalTotAmt = 0;
		
		double curr_debit_Amt = 0;
		double curr_Outstanding_Amt =0;
		
		
		try {
			
			int transactioncount = 0;
			
			conn = DbConnection.getConnection();
		
			/*String getItemName = "select Value from maintenance_master where Unique_ID = ?";
			psmt2 = conn.prepareStatement(getItemName);
			psmt2.setString(1,UpdateTransactionMap.get("Upd_TransactionItemID_key") );
			rSet2 = psmt2.executeQuery();
			while(rSet2.next()){ItemName = rSet2.getString("Value");}*/
			
			String getQuery = "SELECT * from cust_bill_info where Bill_Id = ?";
			psmt1 = conn.prepareStatement(getQuery);
			psmt1.setString(1,UpdateTransactionMap.get("Upd_TransactionBillId_key"));
			rSet1 = psmt1.executeQuery();
			while(rSet1.next()){OldAmount = rSet1.getInt("Bill_Amt");}
			
			NewAmount = Float.valueOf(UpdateTransactionMap.get("Upd_TransactionAmount_key"));
			DiffAmount =  NewAmount - OldAmount;
			
			String infoUpdate = "UPDATE cust_bill_info SET Bill_Date = ?,Bill_Amt= ?, Bill_Item_Id = ?, Bill_Item_Name = ?, Description =?, Modified_Dt = SYSDATE() WHERE Bill_Id = ? ";
			psmt3 = conn.prepareStatement(infoUpdate);
			psmt3.setString(1,UpdateTransactionMap.get("Upd_TransactionDate_key"));
			psmt3.setString(2,UpdateTransactionMap.get("Upd_TransactionAmount_key"));
			psmt3.setString(3,UpdateTransactionMap.get("Upd_TransactionItemID_key"));
			psmt3.setString(4,null);
			psmt3.setString(5,UpdateTransactionMap.get("Upd_TransactionDescription_key"));
			psmt3.setString(6,UpdateTransactionMap.get("Upd_TransactionBillId_key"));
			transactioncount = psmt3.executeUpdate();
			
			System.out.println("11111111111111111111111111111111-transactioncount->"+transactioncount);
			if(transactioncount > 0){
				String curr_DR_Amt = "SELECT Cust_DR_Amt,Cust_Outstanding from customer_info where Cust_ID = ?";
				psmt4 = conn.prepareStatement(curr_DR_Amt);
				psmt4.setString(1,UpdateTransactionMap.get("Upd_TransactionCustIID_key"));
				rSet4 = psmt4.executeQuery();
				while(rSet4.next()){
					curr_debit_Amt = rSet4.getInt("Cust_DR_Amt");	
					curr_Outstanding_Amt = rSet4.getInt("Cust_Outstanding");	
				}
				
				double updt_DR_Amt = curr_debit_Amt + DiffAmount;
				double updt_Outstanding_Amt = curr_Outstanding_Amt - DiffAmount;
				
				String MasterUpdate = "UPDATE customer_info SET Cust_DR_Amt = ?,Cust_Outstanding = ?,Modified_Dt =SYSDATE()	where Cust_ID = ?";
				psmt5 = conn.prepareStatement(MasterUpdate);
				psmt5.setDouble(1, updt_DR_Amt);
				psmt5.setDouble(2, updt_Outstanding_Amt);
				psmt5.setString(3, UpdateTransactionMap.get("Upd_TransactionCustIID_key"));
				transactioncount = psmt5.executeUpdate();
				System.out.println("5555555555555555555555555555555555555-transactioncount->"+transactioncount);
				if(transactioncount > 0){
					System.out.println("666666666666666666666666666666666666666666666666666-->");
					InsertNewDebitTransaction = "Success";
				}
				else
				{
					InsertNewDebitTransaction = "Failed";
				}
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
		
		
		return InsertNewDebitTransaction;
		
		
		
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
