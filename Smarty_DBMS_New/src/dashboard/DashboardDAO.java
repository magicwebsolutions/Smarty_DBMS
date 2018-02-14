package dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbConnection.DbConnection;

public class DashboardDAO {
	
	public String ListDashboardData(){
		
		
		System.out.println("INTO DahsboardDAO CLASSS");
		StringBuffer dashboard_data = null;
		Connection conn =null;
		String outstandingQuery = "SELECT Cust_ID,Cust_Name,Cust_Outstanding FROM Customer_Info where Cust_Status='ACTIVE' and Cust_Outstanding > 0 order by Cust_Outstanding desc limit 10";
		String salesItemRatioQuery = "SELECT (SELECT value FROM maintenance_master WHERE Unique_ID = Bill_Item_Id) AS ITEM, SUM(Bill_Amt) as AMT FROM cust_bill_info WHERE Bill_Item_Id IS NOT NULL GROUP BY Bill_Item_Id;";
		ResultSet rSet = null;
		ResultSet rSet1 = null;
		PreparedStatement psmt = null;
		PreparedStatement psmt1 = null;
		try{
				dashboard_data = new StringBuffer();
				conn = DbConnection.getConnection();			
				psmt = conn.prepareStatement(outstandingQuery);
				rSet=psmt.executeQuery();
					while(rSet.next()){
						dashboard_data.append(rSet.getString("Cust_ID"));
						dashboard_data.append("~");
						dashboard_data.append(rSet.getString("Cust_Name"));
						dashboard_data.append("~");
						dashboard_data.append(rSet.getString("Cust_Outstanding"));
						dashboard_data.append("#");		
					}
					dashboard_data.append("|");	
					
					System.out.println("Query for for search item--->"+dashboard_data);		
					psmt1 = conn.prepareStatement(salesItemRatioQuery);
					rSet1 = psmt1.executeQuery();
					while(rSet1.next()){
						dashboard_data.append(rSet1.getString("ITEM"));
						dashboard_data.append("~");
						dashboard_data.append(rSet1.getString("AMT"));
						dashboard_data.append("#");		
					}
					
					
		}
		catch(Exception e){
			System.out.println("Something went wrong during Unit........");
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
		return dashboard_data.toString();
	}

}
