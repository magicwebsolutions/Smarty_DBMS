package settings;

import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class DeleteCustomerDAO {

	public String deleteCustomerDtls(HashMap deleteCustData){
		
		Connection conn =null;
		ResultSet rSet = null;
		
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;
		
		String UpdatetStatus = "Failed";
		
		try{
			int insertedRow = 0;
			
			String Delete_Customer_Dtls="UPDATE customer_info SET Cust_Status='INACTIVE',Modified_Dt=SYSDATE() where cust_id=?";
			conn = (Connection) dbConnection.DbConnection.getConnection();
			psmt1 = (PreparedStatement) conn.prepareStatement(Delete_Customer_Dtls);
			psmt1.setString(1, (String)deleteCustData.get("Delete_CustId_key"));
			insertedRow = psmt1.executeUpdate();
			if(insertedRow > 0){				
				UpdatetStatus = "Success";
			}
			else{
				UpdatetStatus = "Failed";
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
		
		return UpdatetStatus;
	}
	
	public String NullCheck(String input){
		if(input==null || input==""){
			input="000000";			
		}
		return input;		
	}
}
