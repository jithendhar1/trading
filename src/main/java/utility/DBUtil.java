package utility;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {
	private static Connection conn;

	public DBUtil() {
	}

	public static Connection provideConnection() {
	    try {
	        if (conn == null || conn.isClosed()) {
	            ResourceBundle rb = ResourceBundle.getBundle("application");
	            String connectionString = rb.getString("jdbc.url");
	            String driverName = rb.getString("jdbc.driver");
	            String username = rb.getString("jdbc.username");
	            String password = rb.getString("jdbc.password");

	            try {
	                Class.forName(driverName);
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	            conn = DriverManager.getConnection(connectionString, username, password);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return conn;
	}


	public static void closeConnection(Connection con) {
		/*
		 * try { if (con != null && !con.isClosed()) {
		 * 
		 * con.close(); } } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	public static void closeConnection(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeConnection(PreparedStatement ps) {
		try {
			if (ps != null && !ps.isClosed()) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
