package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author 문진수
 * 제로베이스 백엔드 3기 
 *
 *
 */

public interface CommandHandler {
	public String process(HttpServletRequest req,
			HttpServletResponse res) throws Exception; 
	//req 와 res를 parameter로 갖는 process 메소드

}
