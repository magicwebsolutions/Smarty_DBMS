package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static String JDBC_URL = "jdbc:mysql://localhost:3306/smarty_dbms";
	public static String USER_NAME = "root";
	public static String PASSWORD = "1234";
	public static String query = "";
	public static Connection conf =null;
	public static Connection getConnection(){
		try{
		Class.forName(JDBC_DRIVER).newInstance();	
		conf = DriverManager.getConnection(JDBC_URL,USER_NAME,PASSWORD);
		}catch(Exception e){
			e.printStackTrace();
			
		}
return conf;
}
}

