package jdbcdemo;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Driver {
	
	
	
	public static void main (String[] args) {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("demo.properties"));
			
			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			
			Connection myConn = DriverManager.getConnection(url, user, password);
			System.out.println("Sucesso!!!");
		}
			
			
		/**Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root", "root");
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("Select * from employees");
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + "," + myRs.getString("first_name"));
			}
			
		}**/
		
		catch (Exception exc) {
			exc.printStackTrace();
			
		}
		
	}

}
