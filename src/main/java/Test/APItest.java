package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class APItest {
	public static void main(String[] args) {
		
		  // 미세먼지 경보 정보 조회 서비스 - 한국환경공단 api 서비스
        try {
            // 인증키
            String serviceKey = "인증키값";
            
            String urlStr = "http://openapi.seoul.go.kr:8088/4a576958656d6f6f3937506f48747a/json/TbPublicWifiInfo/1/15/";
            
            URL url = new URL(urlStr);
            
            String line = "";
            String result = "";
            
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = br.readLine()) != null) {
                result = result.concat(line);
                //System.out.println(line);                
            }            
            
            // JSON parser 만들어 문자열 데이터를 객체화한다.
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(result);
            JSONObject TbPublicWifiInfo = (JSONObject)obj.get("TbPublicWifiInfo");
            
            JSONArray parse_listArr = (JSONArray)TbPublicWifiInfo.get("row");
            for (int i=0;i< parse_listArr.size();i++) {
                JSONObject wifi = (JSONObject) parse_listArr.get(i);
                String adminNumber = (String) wifi.get("X_SWIFI_MGR_NO");           
                String borough = (String) wifi.get("X_SWIFI_WRDOFC");    
                String wifiName = (String) wifi.get("X_SWIFI_MAIN_NM");          
                String AddressName1 = (String) wifi.get("X_SWIFI_ADRES1");    
                String AddressName2 = (String) wifi.get("X_SWIFI_ADRES2");            
                String floor = (String) wifi.get("X_SWIFI_INSTL_FLOOR");    
                String add1 = (String) wifi.get("X_SWIFI_INSTL_TY");            
                String add2 = (String) wifi.get("X_SWIFI_INSTL_MBY");   
                String add3 = (String) wifi.get("X_SWIFI_CNSTC_YEAR");    // 발령지역
                
                
                
                StringBuffer sb = new StringBuffer();
                sb.append("adminNumber").append(adminNumber).append('\n');
                sb.append("borough").append(borough).append('\n');
                sb.append("wifiName").append(wifiName).append('\n');
                sb.append("AddressName1").append(AddressName1).append('\n');
                sb.append("AddressName2").append(AddressName2).append('\n');
                sb.append("floor").append(floor).append('\n');
                sb.append("add1").append(add1).append('\n');
                sb.append("add2").append(add2).append('\n');
                sb.append("add3").append(add3).append('\n');
                System.out.println(sb.toString());                
            }
            
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
		
		
		
		

}
