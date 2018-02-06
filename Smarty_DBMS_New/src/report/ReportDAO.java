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
		String query = "SELECT Cust_ID,date(Modified_Dt) as Modified_Dt ,Cust_Name,Cust_CR_Amt,Cust_DR_Amt,Cust_Outstanding FROM Customer_Info where Cust_Status='ACTIVE' and Modified_Dt between '"+fromDate+"' and "+"DATE_ADD('"+toDate+"', INTERVAL 1 DAY) and (cust_cr_amt != 0 or cust_dr_amt !=0)";
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
				summaryBuffer.append(rSet.getString("Modified_Dt"));
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
		System.out.println("summaryBuffer.toString()---->"+summaryBuffer.toString());		
		return summaryBuffer.toString();
		
	}
	
	
	
	public String getCustomerDetailedReport(String customerid){
		StringBuffer CustsummaryBuffer = null;
		Connection conn =null;
		String query = "select bill_id,bill_date,cust_Name,bill_type,bill_amt,description from cust_bill_info where cust_id= ? order by Modified_Dt desc";
		String CustomerInfoquery = "select cust_phone,cust_address,cust_outstanding from customer_info where cust_id= ?";
		ResultSet rSet = null;
		ResultSet rSet1 = null;
		PreparedStatement psmt = null;
		PreparedStatement psmt1 = null;
		try{
			CustsummaryBuffer = new StringBuffer();
			conn = DbConnection.getConnection();			
			 psmt = conn.prepareStatement(query);
			 psmt.setString(1, customerid);
			 
			 System.out.println("Query for for getIncomeExpenseSummary--->"+customerid);	
			 System.out.println("Query for for getIncomeExpenseSummary--->"+query);		
			 rSet=psmt.executeQuery();
			while(rSet.next()){
				CustsummaryBuffer.append(rSet.getString("bill_id"));
				CustsummaryBuffer.append("~");
				CustsummaryBuffer.append(rSet.getString("bill_date"));
				CustsummaryBuffer.append("~");
				CustsummaryBuffer.append(rSet.getString("cust_Name"));
				CustsummaryBuffer.append("~");
				CustsummaryBuffer.append(rSet.getString("bill_type"));
				CustsummaryBuffer.append("~");
				CustsummaryBuffer.append(rSet.getString("bill_amt"));
				CustsummaryBuffer.append("~");
				CustsummaryBuffer.append(rSet.getString("description"));
				CustsummaryBuffer.append("#");
			}
			CustsummaryBuffer.append("|");
			psmt1 =conn.prepareStatement(CustomerInfoquery);
			psmt1.setString(1, customerid);
			rSet1=psmt1.executeQuery();
		    while(rSet1.next()){
		    	CustsummaryBuffer.append(rSet1.getString("cust_phone"));
		    	CustsummaryBuffer.append("~");
		    	CustsummaryBuffer.append(rSet1.getString("cust_address"));
		    	CustsummaryBuffer.append("~");
		    	CustsummaryBuffer.append(rSet1.getString("cust_outstanding"));
		    }			
			System.out.println("ResultSet for CustsummaryBuffer--->"+CustsummaryBuffer);
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
		System.out.println("CustsummaryBuffer.toString()---->"+CustsummaryBuffer.toString());		
		return CustsummaryBuffer.toString();
		
	}
}
