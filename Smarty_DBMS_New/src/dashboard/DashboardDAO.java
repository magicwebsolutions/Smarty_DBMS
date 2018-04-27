package dashboard;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import dbConnection.DbConnection;

public class DashboardDAO {

	public String ListDashboardData() {

		StringBuffer dashboard_data = null;
		Connection conn = null;
		String outstandingQuery = "SELECT Cust_ID,Cust_Name,Cust_Outstanding FROM Customer_Info where Cust_Status='ACTIVE' and Cust_Outstanding > 0 order by Cust_Outstanding desc limit 10";
		String salesItemRatioQuery = "SELECT (SELECT value FROM maintenance_master WHERE Unique_ID = Bill_Item_Id) AS ITEM, SUM(Bill_Amt) as AMT FROM cust_bill_info WHERE Bill_Item_Id IS NOT NULL GROUP BY Bill_Item_Id;";
		ResultSet rSet = null;
		ResultSet rSet1 = null;
		PreparedStatement psmt = null;
		PreparedStatement psmt1 = null;
		try {
			dashboard_data = new StringBuffer();
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(outstandingQuery);
			rSet = psmt.executeQuery();
			while (rSet.next()) {
				dashboard_data.append(rSet.getString("Cust_ID"));
				dashboard_data.append("~");
				dashboard_data.append(rSet.getString("Cust_Name"));
				dashboard_data.append("~");
				dashboard_data.append(rSet.getString("Cust_Outstanding"));
				dashboard_data.append("#");
			}
			dashboard_data.append("|");

			psmt1 = conn.prepareStatement(salesItemRatioQuery);
			rSet1 = psmt1.executeQuery();
			while (rSet1.next()) {
				dashboard_data.append(rSet1.getString("ITEM"));
				dashboard_data.append("~");
				dashboard_data.append(rSet1.getString("AMT"));
				dashboard_data.append("#");
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				if (rSet != null) {
					rSet.close();

				}
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}
		return dashboard_data.toString();
	}

	/* For Email Trigger Methods */

	public static String mailProcess() {
		Connection conn = null;
		ResultSet rSet = null;
		PreparedStatement psmt = null;
		String configDtlsQuery = "select configname,configvalue from configuration";
		String bakcupPath = "";
		String sqlDumpPath = "";
		String sqlUname = "";
		String sqlPwd = "";
		String sqlDbName = "";
		/*
		 * String backupLocalFlag = "N"; String backupMailFlag = "N";
		 */

		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(configDtlsQuery);
			rSet = psmt.executeQuery();
			while (rSet.next()) {
				if (rSet.getString("configname").equals("BACKUPPATH")) {
					bakcupPath = rSet.getString("configvalue");
				}
				if (rSet.getString("configname").equals("SQLDUMPPATH")) {
					sqlDumpPath = rSet.getString("configvalue");
				}
				if (rSet.getString("configname").equals("SQLDBUSERNAME")) {
					sqlUname = rSet.getString("configvalue");
				}
				if (rSet.getString("configname").equals("SQLDBPWD")) {
					sqlPwd = rSet.getString("configvalue");
				}
				if (rSet.getString("configname").equals("SQLDBNAME")) {
					sqlDbName = rSet.getString("configvalue");
				}
				/*
				 * if(rSet.getString("configname").equals("BACKUPLOCAL")){
				 * backupLocalFlag = rSet.getString("configvalue"); }
				 * if(rSet.getString("configname").equals("BACKUPMAIL")){
				 * backupMailFlag = rSet.getString("configvalue"); }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rSet != null) {
					rSet.close();
				}
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
		String filenameformed = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());

		boolean connectionExist = false;
		String createBkup = null;
		final String filename = bakcupPath.replaceAll("\"", "\\") + "\\" + filenameformed;
		String result = null;
		try {
			connectionExist = checkConnection();
			if (connectionExist) {
				createBkup = createBkup(filename, sqlDumpPath, sqlUname, sqlPwd, sqlDbName);
				if (createBkup != null && createBkup.equals("SUCCESS")) {
					result = triggerMail(filename);
				} else {
					result = "BKUP_FAIL";
				}
			} else {
				result = "NO_INT_CONN";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return result;
		}
	}

	public static String triggerMail(String filename) {
		String result = null;
		String to = "magicwebsolz@gmail.com";// change accordingly
		final String user = "magicweb.dbms.id1@gmail.com";// change accordingly
		final String password = "magic@12345";// change accordingly

		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.ssl.trust", host);
		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.ssl.trust", host);
		props.put("mail.smtp.user", user);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Backup for the data::" + new java.util.Date());
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("This is message body");

			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);

			message.setContent(multipart);

			Transport.send(message);
			result = "MAIL_TRIGGERED";

		} catch (MessagingException ex) {
			ex.printStackTrace();
			result = "MAIL_TRIGGER_FAIL";
		}
		return result;
	}

	public static String createBkup(String fileName, String sqlDumpPath, String sqlUname, String sqlPwd,
			String sqlDbName) {
		Process p = null;
		String result = null;

		String formation = sqlDumpPath + "\\" + "mysqldump " + "-u" + sqlUname + " -p" + sqlPwd
				+ " --add-drop-database -B " + sqlDbName + " -r";
		try {
			Runtime runtime = Runtime.getRuntime();
			/*
			 * p = runtime
			 * .exec("C:\\Program Files (x86)\\MySQL\\MySQL Server 5.0\\bin\\mysqldump -uroot -p1234 --add-drop-database -B smarty_dbms -r "
			 * + fileName);
			 */
			p = runtime.exec(formation + fileName);
			int processComplete = p.waitFor();
			if (processComplete == 0) {
				result = "SUCCESS";
			} else {
				result = "FAIL";
			}
		} catch (Exception e) {
			result = "FAIL";
			e.printStackTrace();
		}
		return result;
	}

	public static boolean checkConnection() {
		boolean flag = false;
		try {
			URL url = new URL("http://www.goal.com");
			URLConnection connection = url.openConnection();
			connection.connect();
			flag = true;
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			flag = false;
		} catch (IOException ex) {
			ex.printStackTrace();
			flag = false;
		} catch (Exception ex) {
			ex.printStackTrace();
			flag = false;
		} finally {
			return flag;
		}
	}

}
