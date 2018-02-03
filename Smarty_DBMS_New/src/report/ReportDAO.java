package report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbConnection.DbConnection;

public class ReportDAO {
	public String getIncomeExpenseSummary(String fromDate,String toDate){
		StringBuffer summaryBuffer = null;
		Connection conn =null;
		String query = "SELECT Cust_ID,Cust_Name,Cust_CR_Amt,Cust_DR_Amt,Cust_Outstanding FROM Customer_Info where Cust_Status='ACTIVE' and Modified_Dt between"+fromDate+"and"+"DATE_ADD("+toDate+", INTERVAL 1 DAY)";
		ResultSet rSet = null;
		PreparedStatement psmt = null;
		try{
			summaryBuffer = new StringBuffer();
			conn = DbConnection.getConnection();			
			 psmt = conn.prepareStatement(query);
			 System.out.println("Query for for getIncomeExpenseSummary--->"+query);		
			 rSet=psmt.executeQuery();
			while(rSet.next()){
				summaryBuffer.append(rSet.getString("Cust_ID"));
				summaryBuffer.append("~");
				summaryBuffer.append(rSet.getString("Cust_Name"));
				summaryBuffer.append("~");
				summaryBuffer.append(rSet.getString("Cust_CR_Amt"));
				summaryBuffer.append("~");
				summaryBuffer.append(rSet.getString("Cust_DR_Amt"));
				summaryBuffer.append("~");
				summaryBuffer.append(rSet.getString("Cust_Outstanding"));
				summaryBuffer.append("#");		
			}
			System.out.println("ResultSet for getIncomeExpenseSummary--->"+summaryBuffer);
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
		
		
		return summaryBuffer.toString();
		
	}
}
