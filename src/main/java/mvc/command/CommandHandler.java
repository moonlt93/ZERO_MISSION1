package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	public String process(HttpServletRequest req,
			HttpServletResponse res) throws Exception;//허용되지 않는 예외처리 던지기 호출하는 메소드에 책임을 전가 
	//파라미터로 req,res를 가지는 프로세스 추상 메소드를 가짐.
	//추상메소드는 선언만 해둔 메소드 구현 객체가 필요한 임의의 메소드 중 공통된 부분을 모은것으로 암.

}
