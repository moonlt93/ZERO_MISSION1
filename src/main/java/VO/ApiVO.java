package VO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ApiVO {
	
	private String AuthNum;
	private String Resident;
	private String WifiName;
	private String RoadAdd;
	private String DetailAdd;
	private String InstallSpot;
	private String InstallType;
	private String Agency;
	private String ServiceType;
	private String InstallDate;
	private String SideType;
	private String NetWorkSpot;
	private String ConnectView;
	private Double Latitude;
	private Double Longitude;
	private double distance;
	private String ContactDate;
	private long totalNum;

	
	
	
	
	  public ApiVO from(ResultSet rs) throws SQLException {
	 
		  ApiVO Api = new ApiVO();
			  Api.setAuthNum(rs.getString("AuthNum"));
			  Api.setResident(rs.getString("Resident"));
			  Api.setWifiName(rs.getString("WifiName"));
			  Api.setRoadAdd(rs.getString("RoadAdd"));
			  Api.setDetailAdd(rs.getString("DetailAdd"));
			  Api.setInstallSpot(rs.getString("InstallSpot"));
			  Api.setInstallType(rs.getString("InstallType"));
			  Api.setAgency(rs.getString("Agency"));
			  Api.setServiceType(rs.getString("ServiceType"));
			  Api.setSideType(rs.getString("SideType"));
			  Api.setInstallDate(rs.getString("InstallDate"));            
			  Api.setConnectView(rs.getString("ConnectView"));
			  Api.setLatitude(rs.getDouble("Latitude"));
			  Api.setLongitude(rs.getDouble("Longitude"));
			  Api.setContactDate(rs.getString("ContactDate"));
			  Api.setDistance(Math.round((rs.getDouble("BD")* 10000 ) / 10000.0));
			
			  return Api;
		  
	  }
	  

	  
	  
	  
	  }

