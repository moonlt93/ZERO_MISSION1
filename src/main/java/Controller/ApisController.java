package Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.ApiDTO;
import Service.ApiService;
import Service.HistoryService;
import mvc.command.CommandHandler;

public class ApisController implements CommandHandler {

	private static String FORM_VIEW = "index";

	private ApiService apiService = new ApiService();
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

		int number = historyService.InsertHistory(req);

		if (number > 0) {

			double x1 = Double.parseDouble(req.getParameter("latitude"));
			double y1 = Double.parseDouble(req.getParameter("longitude"));

			List<ApiDTO> list = apiService.selectList(x1, y1);

			req.setAttribute("list", list);

		}
		return FORM_VIEW;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {

		if ("".equals(req.getParameter("latitude")) || req.getParameter("longitude") == null) {

			long total = apiService.InsertThings();
			req.setAttribute("total", total);
			return "Downloading";

		}

		return "index";
	}

}
