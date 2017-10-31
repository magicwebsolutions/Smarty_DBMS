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
		String outstandingQuery = "SELECT Cust_ID,Cust_Name,Cust_Outstanding FROM Customer_Info where Cust_Status='ACTIVE'";
		String salesRatioQuery = "select bill_Item_Name,sum(Bill_Amt) from cust_bill_info where Bill_Type = 'CR' group by bill_Item_Id;";
		ResultSet rSet = null;
		int i =0;
		PreparedStatement psmt = null;
		try{
				dashboard_data = new StringBuffer();
				conn = DbConnection.getConnection();			
				psmt = conn.prepareStatement(outstandingQuery);
				System.out.println("Query for for search item--->"+outstandingQuery);		
				rSet=psmt.executeQuery();
					while(rSet.next()){
						dashboard_data.append(rSet.getString("Cust_ID"));
						dashboard_data.append("~");
						dashboard_data.append(rSet.getString("Cust_Name"));
						dashboard_data.append("~");
						dashboard_data.append(rSet.getString("Cust_Outstanding"));
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
		System.out.println("sdlkfjsdlkfjklsdjfkldsjklfjdsklj---->"+dashboard_data.toString());
		return dashboard_data.toString();
	}

}
