package controller;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ApiDTO;
import domain.HistoryDTO;
import mvc.command.CommandHandler;
import service.ApiService;
import service.HistoryService;
/**
 * 
 * @author 문진수
 * 제로베이스 백엔드 3기 
 *
 *
 */

public class HistoryController  implements CommandHandler {
	
	private static final String FORM_VIEW ="historyList";
	private HistoryService historyService = new HistoryService();
	
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
		
		int no = Integer.parseInt(req.getParameter("ExtraId"));
		
		if(no >= 0) {
			//no 가 not null일때만 삭제
			historyService.deleteHistory(no);
	
		}
		
		return  FORM_VIEW;
		
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		

		  List<HistoryDTO> list = historyService.getHistoryList();
		  
		  req.setAttribute("list", list);

		
		return FORM_VIEW ;
	}

}
