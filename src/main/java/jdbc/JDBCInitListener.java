package jdbc;

import java.sql.Connection;


import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class JDBCInitListener implements ServletContextListener {

	public JDBCInitListener() {
		System.out.println("Listener start");
		// TODO Auto-generated constructor stub
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("destroy Servlet");
		System.out.println("웹 앱 종료");
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent sce) {
		//웹 애플리케이션 초기화 시 선언 
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
