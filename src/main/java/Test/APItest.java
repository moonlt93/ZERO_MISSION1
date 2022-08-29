package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class APItest {
	public static void main(String[] args) {
		
		for (int m = 0; m < args.length; m++) {
        try {
				
	
            
            String urlStr = "http://openapi.seoul.go.kr:8088/4a576958656d6f6f3937506f48747a/json/TbPublicWifiInfo/1/1000/";
            
            URL url = new URL(urlStr);
            
            String line = "";
            String result = "";
            
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = br.readLine()) != null) {
                result = result.concat(line);
            }  
        	 
            System.out.println(result);
            // JSON parser 만들어 문자열 데이터를 객체화한다.
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(result);
            JSONObject TbPublicWifiInfo = (JSONObject)obj.get("TbPublicWifiInfo");
            System.out.println(TbPublicWifiInfo);
            
            JSONArray parse_listArr = (JSONArray)TbPublicWifiInfo.get("row");
            for (int i=0; i< parse_listArr.size(); i++) {
                JSONObject wifi = (JSONObject) parse_listArr.get(i);
                String adminNumber = (String) wifi.get("X_SWIFI_MGR_NO");           
                String resident = (String) wifi.get("X_SWIFI_WRDOFC");    
                String wifiName = (String) wifi.get("X_SWIFI_MAIN_NM");          
                String AddressName1 = (String) wifi.get("X_SWIFI_ADRES1");    
                String AddressName2 = (String) wifi.get("X_SWIFI_ADRES2");            
                String floor = (String) wifi.get("X_SWIFI_INSTL_FLOOR");    
                String add1 = (String) wifi.get("X_SWIFI_INSTL_TY");            
                String add2 = (String) wifi.get("X_SWIFI_INSTL_MBY");   
                String add3 = (String) wifi.get("X_SWIFI_CNSTC_YEAR");  
                Double posx = Double.valueOf((String)wifi.get("LAT"));
                Double posy = Double.valueOf((String)wifi.get("LNT"));
                String datetime = (String)wifi.get("WORK_DTTM");
                
                
                StringBuffer sb = new StringBuffer();
                sb.append("adminNumber").append(" ").append(adminNumber).append('\n');
                sb.append("resident").append(" ").append(resident).append('\n');
                sb.append("wifiName").append(" ").append(wifiName).append('\n');
                sb.append("AddressName1").append(" ").append(AddressName1).append('\n');
                sb.append("AddressName2").append(" ").append(AddressName2).append('\n');
                sb.append("floor").append(" ").append(floor).append('\n');
                sb.append("add1").append(" ").append(add1).append('\n');
                sb.append("add2").append(" ").append(add2).append('\n');
                sb.append("add3").append(" ").append(add3).append('\n');
                sb.append("posx").append(" ").append(posx).append('\n');
                sb.append("posy").append(" ").append(posy).append('\n');
                sb.append("datetime").append(" ").append(datetime).append('\n');
                System.out.println(sb.toString());                
            }
        	 
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }
		
	}

}
