package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * 
 * @author 문진수
 * 제로베이스 백엔드 3기 
 *
 *
 */

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





