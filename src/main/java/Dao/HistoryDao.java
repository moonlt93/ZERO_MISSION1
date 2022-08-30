package Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import VO.HistoryVO;
import jdbc.JdbcUtil;

public class HistoryDao {
	
	
	public int insertHistory(Connection con, HistoryVO vo) {
		  final String sql = "INSERT IGNORE INTO history(MyLatitude,MyLongitude,VisitedDate) "
		  		+ "VALUES (?, ?, ?)";
      PreparedStatement pstmt = null;
    
      try {
      	pstmt = con.prepareStatement(sql);
      	
      	pstmt.setDouble(1, vo.getMyLatitude());
      	pstmt.setDouble(2, vo.getMyLongitude());
      	pstmt.setString(3, vo.getVisitedDate());
      	
          pstmt.executeUpdate();
    
      }catch (SQLException e) {
          System.out.println("historyInsert 실패!");
          e.printStackTrace();
          return -1;
      }finally {
      	JdbcUtil.close(pstmt);
      }
		return 1;
	}
	
	public List<HistoryVO> list(Connection con) throws SQLException{
		String sql="select * from history h order by(ExtraId) desc";
		
		List<HistoryVO> list = new ArrayList<>();
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			HistoryVO vo = new HistoryVO();
			
			vo.setExtraId(rs.getString(1));
			vo.setMyLatitude(rs.getDouble(2));
			vo.setMyLongitude(rs.getDouble(3));
			vo.setVisitedDate(rs.getString(4));
			
			list.add(vo);
		}
		}
		
		return list;
	}

	public void delete(Connection con, int no) throws SQLException {
		String sql = "DELETE from history "
				+ "WHERE ExtraId ="
				+ " ? ";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, no);
			
			pstmt.executeQuery();
		
		}
		

	}


}
