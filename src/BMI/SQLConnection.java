package BMI;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class SQLConnection {

	static Connection conn;

	public static Connection ConnecrDb() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bmi", "root", "");
			// JOptionPane.showMessageDialog(null, "Connected");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Try Again");
			return null;
		}
	}
}
