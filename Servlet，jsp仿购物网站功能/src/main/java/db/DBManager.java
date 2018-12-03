package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;
public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(Driver.class.getName());
		} catch (ClassNotFoundException e) {
			System.out.println("驱动文件未找到!");
		}
		String url = "jdbc:mysql://localhost:3306/items";
		String user = "root";
		String password = "66668888ZH";
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static void closeConnection(Connection conn, PreparedStatement ps) {
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
