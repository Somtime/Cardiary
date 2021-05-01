package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// Select 문 수행 후 리소스 해지 메소드
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
			try {
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
				if (rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	// Insert, Update, Delete 문 수행 후 리소스 해지 메소드
	public static void close(Connection conn, Statement stmt) {
		try {
			if (conn != null) conn.close();
			if (stmt != null) stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
