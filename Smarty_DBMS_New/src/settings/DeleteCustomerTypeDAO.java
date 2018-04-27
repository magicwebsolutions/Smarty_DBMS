package settings;

import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class DeleteCustomerTypeDAO {

	public String deleteCustomerTypeDtls(HashMap deleteCustData){
		
		Connection conn =null;
		ResultSet rSet = null;
		
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;
		
		String DeleteStatus = "Failed";
		
		try{
			int insertedRow = 0;
			
			String Delete_CustomerType_Dtls="UPDATE Maintenance_Master SET Status='INACTIVE',Modified_Dt=SYSDATE() where Unique_ID=?";
			conn = (Connection) dbConnection.DbConnection.getConnection();
			psmt1 = (PreparedStatement) conn.prepareStatement(Delete_CustomerType_Dtls);
			psmt1.setString(1, (String)deleteCustData.get("Delete_CustTypeId_key"));
			insertedRow = psmt1.executeUpdate();
			if(insertedRow > 0){				
				DeleteStatus = "Success";
			}
			else{
				DeleteStatus = "Failed";
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
		
		return DeleteStatus;
	}
	
	public String NullCheck(String input){
		if(input==null || input==""){
			input="000000";			
		}
		return input;		
	}
}
