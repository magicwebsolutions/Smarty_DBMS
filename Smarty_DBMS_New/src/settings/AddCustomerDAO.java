package settings;

import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import java.sql.*;

public class AddCustomerDAO {
	
	public String addNewCustomerDtls(HashMap newCustData){
		Connection conn =null;
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;		
		String InsertNewCustStatus = "Failed";		
		try{
			int insertedRow = 0;			
			
			String NEWCUSTOMER_INSERT="INSERT INTO customer_info(Cust_Type,Cust_Name,Cust_Phone,Cust_Address,Cust_CR_Amt,Cust_DR_Amt,Cust_Outstanding,Cust_Status,Created_dt,Modified_Dt) values (?,?,?,?,0,0,0,'ACTIVE',SYSDATE(),SYSDATE())";
			conn = (Connection) dbConnection.DbConnection.getConnection();
			psmt1 = (PreparedStatement) conn.prepareStatement(NEWCUSTOMER_INSERT);
			psmt1.setString(1, (String)newCustData.get("CustType_key"));
			psmt1.setString(2, (String)newCustData.get("CustName_key"));
			psmt1.setString(3, (String)newCustData.get("CustPhone_key"));
			psmt1.setString(4, (String)newCustData.get("CustAddress_key"));
			//psmt1.setString(5, currentTime);
			//psmt1.setString(6, currentTime);	
			insertedRow = psmt1.executeUpdate();
			if(insertedRow > 0){				
				InsertNewCustStatus = "Success";
			}
			else{
				InsertNewCustStatus = "Failed";
			}
		}catch(SQLException se){
			se.printStackTrace();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			      //finally block used to close resources
			      try{
			         if(psmt1!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   
		}
		
		return InsertNewCustStatus;
	}

}
