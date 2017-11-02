package settings;

import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class AddCustomerTypeDAO {
	
	public String addNewCustomerTypeDtls(HashMap newCustData){
		Connection conn =null;
		ResultSet rSet = null;		
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;		
		String InsertNewCustStatus = "Failed";		
		try{
			int insertedRow = 0;
			
			String NEWCUSTOMERTYPE_INSERT="INSERT INTO Maintenance_Master(Value,Description,Status,Param_1,Created_dt) values (?,?,'ACTIVE','CUST_TYPE',SYSDATE())";
			conn = (Connection) dbConnection.DbConnection.getConnection();
			System.out.println("666666666666666666666666--->"+conn);
			psmt1 = (PreparedStatement) conn.prepareStatement(NEWCUSTOMERTYPE_INSERT);
			psmt1.setString(1, (String)newCustData.get("CustTypeName_key"));
			psmt1.setString(2, (String)newCustData.get("CustTypeDescription_key"));
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
