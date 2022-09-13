package jdbc;

import java.sql.Connection;


import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class JDBCInitListener
 *
 */
@WebListener
public class JDBCInitListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public JDBCInitListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();

		String url = application.getInitParameter("jdbcUrl");
		String user = application.getInitParameter("jdbcUser");
		String pw = application.getInitParameter("jdbcPassword");


		// 1. 클래스 로딩
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(url, user, pw);) {
			System.out.println("Connection Open");
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		ConnectionProvider.setUrl(url);
		ConnectionProvider.setUser(user);
		ConnectionProvider.setPassword(pw);

	

	}

}
