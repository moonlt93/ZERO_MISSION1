package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.DistanceCal;
import VO.ApiVO;
import VO.HistoryVO;
import jdbc.JdbcUtil;


public class apiDao {
	
	 public void insertWifiInfo(Connection con, ApiVO api) throws SQLException {
	        final String sql = "INSERT IGNORE INTO wifiapi VALUES (" +
	                    " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	        PreparedStatement pstmt = null;
	      
	        try {
	        	pstmt = con.prepareStatement(sql);
	        	
	            setInsertPreparedStatementFrom(pstmt,api);
	            pstmt.executeUpdate();
	      
	            
	        }catch (SQLException e) {
	            System.out.println("insertWifiInfo 실패!");
	            e.printStackTrace();
	            JdbcUtil.rollback(con);
	        }finally {
	        	JdbcUtil.close(pstmt);
	        }
	 }

	    private void setInsertPreparedStatementFrom(PreparedStatement pstmt, ApiVO api) throws SQLException{
	    	pstmt.setString(1, api.getAuthNum());
	    	pstmt.setString(2, api.getResident());
	    	pstmt.setString(3, api.getWifiName());
	    	pstmt.setString(4, api.getRoadAdd());
	    	pstmt.setString(5, api.getDetailAdd());
	        pstmt.setString(6, api.getNetWorkSpot());
	        pstmt.setString(7, api.getInstallSpot());
	        pstmt.setString(8, api.getAgency());
	        pstmt.setString(9, api.getServiceType());
	        pstmt.setString(10, api.getInstallType());
	        pstmt.setString(11, api.getInstallDate());
	        pstmt.setString(12, api.getSideType());
	        pstmt.setString(13, api.getConnectView());
	        pstmt.setDouble(14, api.getLongitude());
	        pstmt.setDouble(15, api.getLatitude());
	        pstmt.setString(16, api.getContactDate());
	    }
	    

	    public List<ApiVO> selectList(Connection con, double x1, double y1) throws SQLException {
	    	
			
			ResultSet rs = null;//초기화 .
			
			String sql ="select *,"+ buildDistanceQuery(x1,y1)
					+ "FROM wifiapi"
					+" HAVING distance <= 0.5"
					+" ORDER BY (distance) asc "
					+" LIMIT 0, 20";
			
			List<ApiVO> list = new ArrayList<>();
		
			try(PreparedStatement pstmt = con.prepareStatement(sql)) {
				
				rs = pstmt.executeQuery();
			
			while(rs.next()) {
			ApiVO vo = new ApiVO();
				
				list.add(vo.from(rs));
				
			}
		}
			
			return list;
	    }
	    
	    private String buildDistanceQuery(double lat, double log) {
	  
	        final String sql = " (6371*acos(cos(radians(" + log + "))"
	        		+ "*cos(radians(latitude ))*cos(radians(longitude) " +
	                "    -radians(" + lat + "))+sin(radians(" + log + "))*sin(radians(latitude)))) " +
	                "    AS distance ";
	        
	        return sql;
	    }
	    

	
	

}
