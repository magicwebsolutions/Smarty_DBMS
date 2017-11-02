package settings;

import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class EditCustomerTypeDAO {
	
	public String editCustomerTypeDtls(HashMap EditCustTypeData){
		Connection conn =null;
		ResultSet rSet = null;
		
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;
		
		String UpdatetStatus = "Failed";
		
		try{
			int insertedRow = 0;

			String Update_Customer_Dtls="UPDATE Maintenance_Master SET Value=?,Description=?,Modified_Dt=SYSDATE() where Unique_ID=?";
			conn = (Connection) dbConnection.DbConnection.getConnection();
			psmt1 = (PreparedStatement) conn.prepareStatement(Update_Customer_Dtls);
			psmt1.setString(1, (String)EditCustTypeData.get("Edit_CustTypeName_key"));
			psmt1.setString(2, (String)EditCustTypeData.get("Edit_CustTypeDescription_key"));
			psmt1.setString(3, (String)EditCustTypeData.get("Edit_CustTypeID_key"));
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
