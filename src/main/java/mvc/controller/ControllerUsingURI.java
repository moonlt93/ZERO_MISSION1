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
    	map = new HashMap<>(); 
    	
    	ServletConfig config = getServletConfig(); 
    	String configFilePath 
    		= config.getInitParameter("configFile").trim();
    	
    	ServletContext application = getServletContext();
  		String filePath = application
  				.getRealPath(configFilePath);
  		try (FileReader fr = new FileReader(filePath);) 
  		{
  			Properties properties = new Properties();
  			properties.load(fr);
  			
  			Set<Object> keys = properties.keySet();
  			
  			for (Object key : keys) {
  				Object value = properties.get(key);
  				String className = (String) value;
  				
  				try {
  					Class c = Class.forName(className);
  					Object o = c.newInstance();
  					
  					CommandHandler handler = (CommandHandler) o;
  					map.put((String) key, handler);
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
	
	private void process(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String root = request.getContextPath();
	
		String command = "";
		//command
		if (uri.startsWith(root)) 
		{
			command = uri.substring(root.length());
		
		}
		
		CommandHandler handler = map.get(command);
			// null값일때
		if (handler == null) 
		{
			handler = new NullHandler();
		}
		
		String view = null; //view 초기화
		try 
		
		{
		  view = handler.process(request, response);
		} catch (Exception e) 
		
		{
			e.printStackTrace();
		}
		
		if (view != null) 
		//만약 view가 null이 아니면
		{
			if(view.equals("index")) {
				request.getRequestDispatcher(view+".jsp").forward(request, response);
			}else {
			
				request.getRequestDispatcher(prefix + view + suffix)
				.forward(request, response);
			}
			
			
		}
		
	}

}





