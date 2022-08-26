package jdbc;

import java.sql.Connection;


import java.sql.SQLException;

public class JdbcUtil {
		
	public static void rollback(Connection con) 
	{
		try {
			if (con != null) {
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(AutoCloseable... ins) 
	// 자동으로 닫아주는 메소드
	
	{
		for (AutoCloseable i : ins) {
			if (i != null) {
				try {
					i.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}





