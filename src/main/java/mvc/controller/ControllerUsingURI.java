package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

/**
 * Servlet implementation class ControllerUsingURI
 */
//서블릿으로 만들어진 mvc중 컨트롤러 역활.
public class ControllerUsingURI extends HttpServlet  //ControllerUsingURI 클래스 이며, Http servlet의 상속을 받음. 
	{
	private static final long serialVersionUID = 1L; 
	private String prefix = "/WEB-INF/view/"; // 필드 prefix
	private String suffix = ".jsp";// 필드 suffix
	private Map<String, CommandHandler> map; 
	// Map<String,CommandHandler> key는 string 형식 value는 commandHandler를 거침.
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerUsingURI() {
        super();
    }
    
    @Override
    public void init() throws ServletException 
    //init()
    {
    	map = new HashMap<>(); // Map<>형식의 map객체 생성
    	
    	ServletConfig config = getServletConfig(); 
    	// getServletConfig().getInitParameter("파라매터명"); 과 같이 가져올 수 있다
    	// getInitParameter() 이걸로도 파라미터 값가져오기 가능
    	String configFilePath 
    		= config.getInitParameter("configFile").trim();
    	//config에 저장된 파라미터 값을 "configFilePath"라는 객체명으로 저장
    	
    	ServletContext application = getServletContext();
    	//servletcontext클래스는 톰캣 실행시 각 컨텍스트마다 한개의 setvletcontext객체를 생성
    	//sc객체는 실행시 웸application 전체의 자원이나 정보를 바인딩해서 공유
  		String filePath = application
  				.getRealPath(configFilePath);
  		//주어진 가상 경로에 해당하는 실제 경로를 가져옴.
  		// configFiepath에 저장된 config 경로를 getRealPath를 통해 실제 경로로 filepath에 저장해서 사용
  		try (FileReader fr = new FileReader(filePath);) 
  		// filereader클래스의 fr 객체는 filePath를 파라미터 값으로 가짐
  		{
  			Properties properties = new Properties();
  			properties.load(fr);
  			//properties 객체 생성. fr을 읽는다.
  			
  			Set<Object> keys = properties.keySet();
  			//set 클래스 keys라는 이름으로 properties의 key를 저장
  			
  			for (Object key : keys) {
  				Object value = properties.get(key);
  				String className = (String) value;
  			//keys 배열에서 key 이름으로 뽑아냄 
  			//value는 properties에서 key를 뽑아냄.
  				//classname은 value를 형변환해서 가짐.
  				
  				try {
  					Class c = Class.forName(className);
//구체적인 클래스의 타입을 알지 못해도 클래스의 변수 및 메소드 등에 접근하게 해주는 API입니다.(동적 바인딩)
// c라는 변수명으로  classname 객체 값을 반환					
  					Object o = c.newInstance();
  		// Object타입 o는 = c객체에 새로운 인스턴스 생성자를 만든것.
  					
  					CommandHandler handler = (CommandHandler) o;
  				// CommandHandler타입 handler는 형변환 한 o 생성자;
  					//여기 질문
  					map.put((String) key, handler);
  					//map 객체에 (string타입 key와, handler값); 저장.
  				} catch (Exception e) 
  					{
  					e.printStackTrace();
  					}
  			}
  			
  		} catch (Exception e) 
  		
  			{
  			e.printStackTrace();
  			}
  		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	
	//doget 메소드  get방식 보내기
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}
	//doget 메소드  post방식 보내기
	
	private void process(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		//uri는 request객체 
		String root = request.getContextPath();
		//root는 contextPath로 가상경로 받음.
		
	
		String command = "";
		//command
		if (uri.startsWith(root)) 
		// root의 길이만큼 substring해서 command에 저장.
		{
			command = uri.substring(root.length());
		
		}
		
		CommandHandler handler = map.get(command);
		// handler map객체에 command 길이 저장.

		if (handler == null) 
		// handler 가 null일때 
		{
			handler = new NullHandler();
			// 객체 생성.
		}
		
		String view = null; //view 초기화
		try 
		
		{
		  view = handler.process(request, response);
		  //view = handler process메소드 사용.
		} catch (Exception e) 
		
		{
			e.printStackTrace();
		}
		
		if (view != null) 
		//만약 view가 null이 아니면
		{
			request.getRequestDispatcher(prefix + view + suffix)
			//dispatcher를 이용해서 forward 객체 전달.
			.forward(request, response);
		}
		
	}

}





