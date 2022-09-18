package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Data;

/**
 * 
 * @author 문진수
 * 제로베이스 백엔드 3기 
 *
 */
@Data
public class ApiDTO {

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

	public ApiDTO from(ResultSet rs) throws SQLException {

		ApiDTO Api = new ApiDTO();
		Api.setAuthNum(rs.getString("AuthNum"));
		Api.setResident(rs.getString("Resident"));
		Api.setWifiName(rs.getString("WifiName"));
		Api.setRoadAdd(rs.getString("RoadAdd"));
		Api.setDetailAdd(rs.getString("DetailAdd"));
		Api.setNetWorkSpot(rs.getString("NetworkSpot"));
		Api.setInstallSpot(rs.getString("InstallSpot"));
		Api.setAgency(rs.getString("Agency"));
		Api.setServiceType(rs.getString("ServiceType"));
		Api.setInstallType(rs.getString("InstallType"));
		Api.setInstallDate(rs.getString("InstallDate"));
		Api.setSideType(rs.getString("SideType"));
		Api.setConnectView(rs.getString("ConnectView"));
		Api.setLatitude(rs.getDouble("Latitude"));
		Api.setLongitude(rs.getDouble("Longitude"));
		Api.setContactDate(rs.getString("ContactDate"));
		Api.setDistance(Math.round((rs.getDouble("distance") * 10000) / 10000.0));

		return Api;

	}

}
