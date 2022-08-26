package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "null"; // CommandHandler의 구현객체인 nullHandler  process메소드를 구현했지만 null 값을 리턴한다.
	}
}
