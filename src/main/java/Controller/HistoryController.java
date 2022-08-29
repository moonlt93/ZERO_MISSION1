package Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ApiService;
import Service.HistoryService;
import VO.ApiVO;
import VO.HistoryVO;
import mvc.command.CommandHandler;

public class HistoryController  implements CommandHandler {
	
	private static final String FORM_VIEW ="";
	private HistoryService his = new HistoryService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
		
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		

		  List<HistoryVO> list = his.getHistoryList();
		  
		  req.setAttribute("list", list);

		
		return "historyList" ;
	}

}
