package library;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library_db_1",
					"root",
					"123456");
					
					
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
}