package co.kh.dev.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.account.model.AccountDAO;
import co.kh.dev.account.model.AccountVO;

@WebServlet("/memberInsert.do")
public class MemberInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberInsert() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
		// 1.값을 읽어온다
		// 2.테이블저장한다.

		// 1-1. 전송된 값을 UTF-8 세팅.
		PrintWriter out = null;
		try {
			request.setCharacterEncoding("UTF-8");//사용자 데이터 값을 UTF-8로 받겠다
			out = response.getWriter();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charSet=UTF-8");
		// 1-2. 정보 가져오기
		//1. 사용자요청데이터를 읽어온다. 2. CURD 3.그 결과값을 화면에 뿌려준다/ 화면을 설계한다.
		String name = request.getParameter("name");
		String uid = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String MEMBER_INSERT = "INSERT INTO MEMBER VALUES((select NVL(max(no),0)+1 from member),?,?,?,SYSDATE)";

		AccountDAO ad = new AccountDAO();
		AccountVO avo = new AccountVO(null, uid, pwd);
		boolean returnflag = ad.insertDB(avo);

		// 2. 테이블에 저장한다.(프로토콜)
		Connection con = null;
		boolean flag = false;
		String url = "jdbc:oracle:thin:@127.0.0.1:1521/xe";
		String id = "webuser";
		String pw = "123456";
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		//CURD
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement(MEMBER_INSERT);
			pstmt.setString(1, name);
			pstmt.setString(2, uid);
			pstmt.setString(3, pwd);
			System.out.println(name + uid + pwd);
			int count = pstmt.executeUpdate();
			returnflag = (count != 0) ? true : false;
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println(e.toString());
		} 
		if (returnflag) {
			System.out.println("입력성공");
			//3. 출력
			out.println("<h1 align=\"center\">회원가입이 완료되었습니다.</h1>");
			out.println("<table align=\"center\" width=\"300\" border=\"1\">");
			out.println("<tr>");
			out.println("<td align=\"center\">");
			out.println("이름 = " + name);
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<tr>");
			out.println("<td align=\"center\">");
			out.println("id = " + uid);
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"center\">");
			out.println("비밀번호 = " + pwd);
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"center\">");
			out.println("<a href='/jspStudy/loginServlet.do'><input type=\"button\" value=\"로그인\"></a>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");

		} else {
			System.out.println("입력실패");
			if (flag) {
				System.out.println("입력성공");

				out.println("<h1 align=\"center\">회원가입이 실패되었습니다.</h1>");
				out.println("<table align=\"center\" width=\"300\" border=\"1\">");
				out.println("<tr>");
				out.println("<td align=\"center\">");
				out.println("이름 = " + name);
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<tr>");
				out.println("<td align=\"center\">");
				out.println("id = " + uid);
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"center\">");
				out.println("비밀번호 = " + pwd);
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"center\">");
				out.println("<a href='/jspStudy/member/member.html'><input type=\"button\" value=\"회원가입\"></a>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");

			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

}
