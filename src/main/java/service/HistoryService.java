package service;

import java.sql.Connection;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.HistoryDao;
import domain.HistoryDTO;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

/**
 * 
 * @author 문진수 제로베이스 백엔드 3기
 *
 */
public class HistoryService {
	private HistoryDao dao = new HistoryDao();

	public int InsertHistory(HttpServletRequest req) {
		int num = 0;
		Connection conn = ConnectionProvider.getConnection();
		HistoryDTO vo = new HistoryDTO();
		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String now_dt = format.format(now);

			vo.setMyLatitude(Double.parseDouble(req.getParameter("latitude")));
			vo.setMyLongitude(Double.parseDouble(req.getParameter("longitude")));
			vo.setVisitedDate(now_dt);

			num = dao.insertHistory(conn, vo);
			return num;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return num;
	}

	public List<HistoryDTO> getHistoryList() {
		Connection con = ConnectionProvider.getConnection();

		List<HistoryDTO> list;
		try {
			list = dao.list(con);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return list;
	}

	public void deleteHistory(int no) {

		Connection con = ConnectionProvider.getConnection();
		try {
			con.setAutoCommit(false);
			dao.delete(con, no);
			con.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		}

	}

}
