package co.kh.dev.account;

import java.io.IOException;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.account.model.AccountDAO;
import co.kh.dev.account.model.AccountVO;

@WebServlet(name = "loginCheckServlet.do", urlPatterns = { "/loginCheckServlet.do" })
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginCheckServlet() {
		super();
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		
		AccountDAO ad = new AccountDAO();
		AccountVO avo= new AccountVO(null, id, pwd);
		AccountVO ravo = ad.selectLoginCheckDB(avo);
		
		
		if(ravo == null) {
			//아이디가 잘못된것
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("id", ravo.getId());
			session.setAttribute("pwd", ravo.getPwd());
			session.setAttribute("name", ravo.getName());
		}
		
			response.sendRedirect("loginServlet.do");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		processRequest(request, response);
	}

}
