package settings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dbConnection.*;

public class ListCustomerDAO {
	
	public String ListCustomer() {
		StringBuffer Unit_Buffer = null;
		Connection conn =null;
		String query = "SELECT Cust_ID,Cust_Name,Cust_Phone,Cust_Address,Cust_CR_Amt,Cust_DR_Amt,Cust_Outstanding FROM Customer_Info where Cust_Status='ACTIVE'";
		ResultSet rSet = null;
		int i =0;
		PreparedStatement psmt = null;
		try{
			Unit_Buffer = new StringBuffer();
			conn = DbConnection.getConnection();			
			 psmt = conn.prepareStatement(query);
			 rSet=psmt.executeQuery();
			while(rSet.next()){
				Unit_Buffer.append(rSet.getString("Cust_ID"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Cust_Name"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Cust_Phone"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Cust_Address"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Cust_CR_Amt"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Cust_DR_Amt"));
				Unit_Buffer.append("~");
				Unit_Buffer.append(rSet.getString("Cust_Outstanding"));
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

}
