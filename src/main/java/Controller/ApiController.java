package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Service.ApiService;

/**
 * Servlet implementation class ApiController
 */
@WebServlet("/API.do")
public class ApiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private final ApiService service = new ApiService();
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset =utf-8");
		
    try {
            
            String urlStr = "http://openapi.seoul.go.kr:8088/4a576958656d6f6f3937506f48747a/json/TbPublicWifiInfo/1/5/";
            
            URL url = new URL(urlStr);
            
            String line = "";
            String result = "";
            
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = br.readLine()) != null) {
                result = result.concat(line);
            }            
            
            // JSON parser 만들어 문자열 데이터를 객체화한다.
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(result);
            JSONObject TbPublicWifiInfo = (JSONObject)obj.get("TbPublicWifiInfo");
            JSONArray parse_listArr = (JSONArray)TbPublicWifiInfo.get("row");
            Object jsonOb = TbPublicWifiInfo.get("row");
            int len =parse_listArr.size();
            
            JSONObject json = new JSONObject();
            json.put("data", parse_listArr);
            json.put("length", len);
		
	}catch(Exception e) {
		e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset =utf-8");
		
    try {
            
            String urlStr = "http://openapi.seoul.go.kr:8088/4a576958656d6f6f3937506f48747a/json/TbPublicWifiInfo/1/15/";
            
            URL url = new URL(urlStr);
            
            String line = "";
            String result = "";
            
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = br.readLine()) != null) {
                result = result.concat(line);
            }            
            
            // JSON parser 만들어 문자열 데이터를 객체화한다.
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(result);
            JSONObject TbPublicWifiInfo = (JSONObject)obj.get("TbPublicWifiInfo");
            Object jsonOb = TbPublicWifiInfo.get("row");
            JSONArray parse_listArr = (JSONArray)TbPublicWifiInfo.get("row");
            
            JSONObject json = new JSONObject();
            json.put("data", parse_listArr);
            json.put("size", parse_listArr.size());
            System.out.println(json);
            
			 res.getWriter().print(json.toJSONString()); 
			
          
	}catch(Exception e) {
		e.printStackTrace();
		}

	}

}
