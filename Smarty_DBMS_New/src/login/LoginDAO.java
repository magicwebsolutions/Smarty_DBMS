package login;

import java.util.*;
import java.sql.*;
public class LoginDAO {
	public String validateUser(HashMap l_Map){
		String returnFlag = "FALSE";
		String Loginquery ="";
		String hint_1=(String) l_Map.get("Hint");
		int count=0;
		try{
			Connection conn = dbConnection.DbConnection.getConnection();
			PreparedStatement psmt = null;
			ResultSet rSet=null;
		if(l_Map.get("flag").equals("Mainlogin"))
		{		
		Loginquery = "SELECT COUNT(*)as count FROM login_master WHERE Username= ? AND Password = ?";
		psmt=conn.prepareStatement(Loginquery);
		psmt.setString(1,(String)l_Map.get("USER_NAME") );
		psmt.setString(2,(String)l_Map.get("USER_PWD") );
		rSet = psmt.executeQuery();
		while(rSet.next()){
			count=rSet.getInt("count");
		}
		if(count>0){
			returnFlag ="TRUE";
		}
		}
		else if(l_Map.containsValue("Sublogin"))
		{
		String key=null;
		Loginquery = "UPDATE user_table SET PASSWORD = ? WHERE HINT=?";
		String Hint="SELECT HINT FROM user_table";
		psmt=conn.prepareStatement(Hint);
		rSet=psmt.executeQuery();
		while(rSet.next()){
			key=rSet.getString("HINT");
		}
		if(hint_1.equalsIgnoreCase(key))
		{
		psmt=conn.prepareStatement(Loginquery);
		psmt.setString(1,(String)l_Map.get("Password") );
		psmt.setString(2,key);
		int a = psmt.executeUpdate();
		if(a==1)
		{
			returnFlag ="UPDATE_PASSWORD";
		}
		}
		else
		{
			returnFlag ="wronghint";
			
			
		}
		}
		
		}
		catch(Exception e){
		e.printStackTrace();
		}
		return returnFlag;
		
	}

}
