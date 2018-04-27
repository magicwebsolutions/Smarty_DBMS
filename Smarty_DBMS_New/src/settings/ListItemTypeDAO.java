package settings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbConnection.DbConnection;

public class ListItemTypeDAO {
	
	public String ListItemType() {
		StringBuffer Unit_Buffer = null;
		Connection conn =null;
		String query = "SELECT Unique_ID,Value,Description,Status,Created_Dt FROM Maintenance_Master where Param_1 = 'ITEM_TYPE'";
		ResultSet rSet = null;
		int i =0;
		PreparedStatement psmt = null;
		try{
				Unit_Buffer = new StringBuffer();
				conn = DbConnection.getConnection();			
				psmt = conn.prepareStatement(query);
				rSet=psmt.executeQuery();
			while(rSet.next()){
				Unit_Buffer.append(rSet.getString("Unique_ID"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Value"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(replaceNull(rSet.getString("Description")));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Status"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Created_Dt"));
				Unit_Buffer.append("#");		
			}
			
			
		
		}
		catch(Exception e){
			e.printStackTrace();
			
		}finally{
			
				try {
					if(rSet!=null){
						rSet.close();
						
					}
					if(psmt!=null){
					psmt.close();
					}
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				
			}
			
		}		
		return Unit_Buffer.toString();
	}
	
	public String replaceNull(String convertText){
			String ReplacedText = "";
				if(convertText == null || convertText == ""){
					ReplacedText = "-";					
				}else
				{
					ReplacedText = convertText.trim();					
				}
			return ReplacedText;
	}


}

