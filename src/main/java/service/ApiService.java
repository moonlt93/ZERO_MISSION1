package service;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import dao.ApiDao;
import domain.ApiDTO;
import jdbc.ConnectionProvider;

public class ApiService {
	private ApiDao dao = new ApiDao();

	public long InsertThings() {
		Connection conn = ConnectionProvider.getConnection();

		long num = 0;
		try {

			// JSON parser 만들어 문자열 데이터를 객체화한다.
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(urlMaker(0));
			JSONObject TbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
			num = (long) TbPublicWifiInfo.get("list_total_count");
			
			ApiDTO vo = null;

			int number = (int) (num / 1000);
			if (num % 1000 > 0) {
				number++;
			}
			for (int j = 0; j < number; j++) {
				JSONObject json = (JSONObject) jsonParser.parse(urlMaker(j));
				JSONObject List = (JSONObject) json.get("TbPublicWifiInfo");
				JSONArray jsonlist = (JSONArray) List.get("row");
				for (int i = 0; i < jsonlist.size(); i++) {
					vo = new ApiDTO();
					JSONObject wifi = (JSONObject) jsonlist.get(i);
					vo.setAuthNum((String) wifi.get("X_SWIFI_MGR_NO"));// 1
					vo.setResident((String) wifi.get("X_SWIFI_WRDOFC"));// 2
					vo.setWifiName((String) wifi.get("X_SWIFI_MAIN_NM"));// 3
					vo.setRoadAdd((String) wifi.get("X_SWIFI_ADRES1"));// 4
					vo.setDetailAdd((String) wifi.get("X_SWIFI_ADRES2"));// 5
					vo.setNetWorkSpot((String) wifi.get("X_SWIFI_INSTL_FLOOR"));
					vo.setInstallSpot((String) wifi.get("X_SWIFI_INSTL_TY"));// 6
					vo.setInstallType((String) wifi.get("X_SWIFI_CMCWR"));// 7
					vo.setAgency((String) wifi.get("X_SWIFI_INSTL_MBY"));// 8
					vo.setServiceType((String) wifi.get("X_SWIFI_SVC_SE"));// 9
					vo.setSideType((String) wifi.get("X_SWIFI_INOUT_DOOR"));// 10
					vo.setInstallDate((String) wifi.get("X_SWIFI_CNSTC_YEAR")); // 11
					vo.setConnectView((String) wifi.get("X_SWIFI_REMARS3"));// 12
					vo.setLatitude(Double.parseDouble((String) wifi.get("LAT")));// 13
					vo.setLongitude(Double.parseDouble((String) wifi.get("LNT")));// 14
					vo.setContactDate((String) wifi.get("WORK_DTTM"));// 15
					dao.insertWifiInfo(conn, vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	public static String urlMaker(int page) throws Exception {
		int start_idx = 1 + (1000 * page);
		int end_idx = 1000 + (1000 * page);
		String key = "4a576958656d6f6f3937506f48747a";
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
		urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode(Integer.toString(start_idx), "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode(Integer.toString(end_idx), "UTF-8"));

		URL url = new URL(urlBuilder.toString());

		BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream()));

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		return sb.toString();
	}

	public List<ApiDTO> selectList(double x1, double y1) {
		Connection conn = ConnectionProvider.getConnection();
		List<ApiDTO> wifi;
		try {

			wifi = dao.selectList(conn, x1, y1);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return wifi;

	}

}
