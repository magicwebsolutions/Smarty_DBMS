package settings;

import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class AddItemTypeDAO {
	
	public String addNewItemTypeDtls(HashMap newCustData){
		Connection conn =null;
		ResultSet rSet = null;		
		PreparedStatement psmt1 = null;
		PreparedStatement psmt2 = null;		
		String InsertNewCustStatus = "Failed";		
		try{
			int insertedRow = 0;
			
			String NEWCUSTOMERTYPE_INSERT="INSERT INTO Maintenance_Master(Value,Description,Status,Param_1,Created_dt) values (?,?,'ACTIVE','ITEM_TYPE',SYSDATE())";
			conn = (Connection) dbConnection.DbConnection.getConnection();
			psmt1 = (PreparedStatement) conn.prepareStatement(NEWCUSTOMERTYPE_INSERT);
			psmt1.setString(1, (String)newCustData.get("ItemTypeName_key"));
			psmt1.setString(2, (String)newCustData.get("ItemTypeDescription_key"));
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
			      try{
			         if(psmt1!=null)
			            conn.close();
			      }catch(SQLException se){
			      }
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			   
		}
		
		return InsertNewCustStatus;
	}

}
