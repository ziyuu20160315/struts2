package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teacher.CustomerBean;
import teacher.CustomerService;

@WebServlet(urlPatterns = { "/secure/login.controller" })
public class LoginServlet extends HttpServlet {
    CustomerService cService =  new CustomerService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//接收
		
		String th1 = request.getParameter("username");
		String th2 = request.getParameter("password");
		
		Map<String, String> errors = new HashMap<>();
		request.setAttribute("error", errors);
		
//轉換
		String username=null;
		if (th1 != null && th1.length() != 0) {
			username=th1;
		}
		
		String password=null;
		if (th2 != null && th2.length() != 0) {
			password=th2;
		}

//驗證 
		if(username==null || username.isEmpty()){
			errors.put("username", "請輸入username");
		}
		
		if(password==null || password.isEmpty()){
			errors.put("password", "請輸入password");
		}
//傳錯誤訊息
		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/secure/login.jsp").forward(request, response);
		}
//call model
		CustomerBean cb =cService.login(username, password);
		if(cb==null){
			errors.put("Login", "登入失敗,請確認 帳號密碼 是否有誤");
			if (errors != null && !errors.isEmpty()) {
				request.getRequestDispatcher("/secure/login.jsp").forward(request, response);
			}
		}
//show view		
		request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
