package co.kh.dev352;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "lifeCycle", urlPatterns = { "/lifeCycle" })
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//디포프 생성자
    public LifeCycle() {
        System.out.println("LifeCycle() 디폴트생성자 호출됨");
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init()  호출됨");
	}

	public void destroy() {
		System.out.println("destroy()  호출됨");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service()  호출됨");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()  호출됨");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		System.out.println("doPost()  호출됨");
	}

}
