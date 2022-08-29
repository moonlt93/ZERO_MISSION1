package Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Dao.apiDao;
import VO.ApiVO;
import VO.HistoryVO;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ApiService {
	private apiDao dao = new apiDao();
	// api 가져온거 처리 
	
	public long InsertThings() {
		Connection conn = ConnectionProvider.getConnection();
		long num= 0;
		try {
			 
				String key="4a576958656d6f6f3937506f48747a";
	            String Type="json";
	            String Service="TbPublicWifiInfo";
	            int Start_Index=1;
	            long END_INDEX= 1000;
	            
	            String urlStr = "http://openapi.seoul.go.kr:8088/"
	                            +key+"/"+Type+"/"+Service+"/"+Start_Index+"/"+END_INDEX+"/";
	            
	            URL url = new URL(urlStr);
	           
	            String line = "";
	            String result = "";
	            
	            BufferedReader br;
	            br = new BufferedReader(new InputStreamReader(url.openStream()));
	            while ((line = br.readLine()) != null) {
	                result = result.concat(line);
	            }            
	            
	            // JSON parser 만들어 문자열 데이터를 객체화한다.
	            
	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
	            JSONObject TbPublicWifiInfo = (JSONObject)jsonObject.get("TbPublicWifiInfo");
	            JSONArray row = (JSONArray)TbPublicWifiInfo.get("row");
	             num=(long)TbPublicWifiInfo.get("list_total_count");
	          
	            ApiVO vo = null;
	        	 int cnt = row.size()/1000;
	             int rest = row.size()%1000;
	             
	            
		            for (int j = 0; j < cnt; j++) {
			             
		            for (int i=0; i< 1000; i++) {
	              vo = new ApiVO();
	              JSONObject wifi = (JSONObject) row.get(j*1000 + i);
	              
	  			  vo.setAuthNum((String) wifi.get("X_SWIFI_MGR_NO"));//1
	  			  vo.setResident((String) wifi.get("X_SWIFI_WRDOFC"));//2
	  			  vo.setWifiName((String) wifi.get("X_SWIFI_MAIN_NM"));//3
	  			  vo.setRoadAdd((String) wifi.get("X_SWIFI_ADRES1"));//4
	  			  vo.setDetailAdd((String) wifi.get("X_SWIFI_ADRES2"));//5
	  			  vo.setNetWorkSpot((String)wifi.get("X_SWIFI_INSTL_FLOOR"));
	  			  vo.setInstallSpot((String) wifi.get("X_SWIFI_INSTL_TY"));//6
	  			  vo.setInstallType((String) wifi.get("X_SWIFI_CMCWR"));//7
	  			  vo.setAgency((String) wifi.get("X_SWIFI_INSTL_MBY"));//8
	  			  vo.setServiceType((String) wifi.get("X_SWIFI_SVC_SE"));//9
	  			  vo.setSideType((String) wifi.get("X_SWIFI_INOUT_DOOR"));//10
	  			  vo.setInstallDate((String) wifi.get("X_SWIFI_CNSTC_YEAR")); //11           
	  			  vo.setConnectView((String) wifi.get("X_SWIFI_REMARS3"));//12
	  			  vo.setLatitude(Double.parseDouble((String) wifi.get("LAT")));//13
	  			  vo.setLongitude(Double.parseDouble((String) wifi.get("LNT")));//14
	  			  vo.setContactDate((String) wifi.get("WORK_DTTM"));//15
	  			  dao.insertWifiInfo(conn, vo);
	          	}
		      }
		     for (int i = 0; i < rest; i++) {
		    	 vo = new ApiVO();
	              JSONObject wifi = (JSONObject)row.get(cnt*1000+ i);
	              
	  			  vo.setAuthNum((String) wifi.get("X_SWIFI_MGR_NO"));//1
	  			  vo.setResident((String) wifi.get("X_SWIFI_WRDOFC"));//2
	  			  vo.setWifiName((String) wifi.get("X_SWIFI_MAIN_NM"));//3
	  			  vo.setRoadAdd((String) wifi.get("X_SWIFI_ADRES1"));//4
	  			  vo.setDetailAdd((String) wifi.get("X_SWIFI_ADRES2"));//5
	  			  vo.setNetWorkSpot((String)wifi.get("X_SWIFI_INSTL_FLOOR"));
	  			  vo.setInstallSpot((String) wifi.get("X_SWIFI_INSTL_TY"));//6
	  			  vo.setInstallType((String) wifi.get("X_SWIFI_CMCWR"));//7
	  			  vo.setAgency((String) wifi.get("X_SWIFI_INSTL_MBY"));//8
	  			  vo.setServiceType((String) wifi.get("X_SWIFI_SVC_SE"));//9
	  			  vo.setSideType((String) wifi.get("X_SWIFI_INOUT_DOOR"));//10
	  			  vo.setInstallDate((String) wifi.get("X_SWIFI_CNSTC_YEAR")); //11           
	  			  vo.setConnectView((String) wifi.get("X_SWIFI_REMARS3"));//12
	  			  vo.setLatitude(Double.parseDouble((String) wifi.get("LAT")));//13
	  			  vo.setLongitude(Double.parseDouble((String) wifi.get("LNT")));//14
	  			  vo.setContactDate((String) wifi.get("WORK_DTTM"));//15
	  			  dao.insertWifiInfo(conn, vo);
		     }

	    
	}catch(Exception e) {
		e.printStackTrace();
		}
		 return num;
	}
	
	
	
	public List<ApiVO> selectList(double x1, double y1) {
		Connection conn = ConnectionProvider.getConnection();
		List<ApiVO> wifi;
		try {
			
			wifi= dao.selectList(conn,x1,y1);	
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return wifi;
	
	}
	
	
}

