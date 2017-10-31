package settings;

import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class EditCustomerDAO {
	
	public String editCustomerDtls(HashMap EditCustData){
		Connection conn =null;
		ResultSet rSet = null;
		
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;
		
		String UpdatetStatus = "Failed";
		
		try{
			int insertedRow = 0;
			
			java.util.Date dt = new java.util.Date();

			java.text.SimpleDateFormat sdf = 
					new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String currentTime = sdf.format(dt);
			
			String Update_Customer_Dtls="UPDATE customer_info SET Cust_Name=?,Cust_Type=?,Cust_Phone=?,Cust_Address=?,Modified_Dt=SYSDATE() where cust_id=?";
			conn = (Connection) dbConnection.DbConnection.getConnection();
			psmt1 = (PreparedStatement) conn.prepareStatement(Update_Customer_Dtls);
			psmt1.setString(1, (String)EditCustData.get("Edit_CustName_key"));
			psmt1.setString(2, (String)EditCustData.get("Edit_CustType_key"));
			psmt1.setInt(3, Integer.parseInt(NullCheck((String)EditCustData.get("Edit_CustPhone_key"))));
			psmt1.setString(4, (String)EditCustData.get("Edit_CustAddress_key"));
			psmt1.setString(5, (String)EditCustData.get("Edit_CustID_key"));
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


