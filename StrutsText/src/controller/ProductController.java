package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teacher.ProductBean;
import teacher.ProductService;

@WebServlet(urlPatterns = { "/pages/product.controller" })

public class ProductController extends HttpServlet {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private ProductService pService = new ProductService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收資料
		String reqId = request.getParameter("id");
		String reqName = request.getParameter("name");
		String reqPrice = request.getParameter("price");
		String reqMake = request.getParameter("make");
		String reqExpire = request.getParameter("expire");
		String prodaction = request.getParameter("prodaction");

		Map<String, String> errors = new HashMap<>();
		request.setAttribute("error", errors);

		// 轉換資料
		

		int id = 0;
		if (reqId != null && reqId.length() != 0) {
			try {
				id = Integer.parseInt(reqId);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("id", "請輸入正確   數字");
			}
		}

		String name = null;
		if (reqName != null && reqName.length() != 0) {
			name = reqName;
		}

		double price = 0;
		if (reqPrice != null && reqPrice.length() != 0) {
			try {
				price = Double.parseDouble(reqPrice);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("price", "請輸入正確  價格");
			}
		}

		java.util.Date make = null;
		if (reqMake != null && reqMake.length() != 0) {
			try {
				make = sdf.parse(reqMake);
			} catch (ParseException e) {
				e.printStackTrace();
				errors.put("make", "請輸入正確   日期  格式 yyyy-MM-dd");
			}
		}

		int expire = 0;
		if (reqExpire != null && reqExpire.length() != 0) {
			try {
				expire = Integer.parseInt(reqExpire);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("expire", "請輸入正確   數字");
			}
		}
		// 驗證資料
		
		if("Insert".equals(prodaction) || "Update".equals(prodaction) || "Delete".equals(prodaction) ){
			if(id==0){
				errors.put("id", prodaction +"時  id 必須輸入");
			}
		}
		
		
		
		
		// 錯誤處理
		if (errors != null && !errors.isEmpty()) {

			request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
			return;

		}
		
		
		// call model
		
		ProductBean pb = new ProductBean();
		pb.setId(id);
		pb.setName(name);
		pb.setPrice(price);
		pb.setMake(make);		
		pb.setExpire(expire);
		
		
		
		
		
		// 顯示給view
		ProductBean bean =null;
		switch (prodaction){
		case "Insert":
			bean=pService.insert(pb);
			request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
			if(bean==null){
				errors.put("InsertSuccess", "新增失敗");
				request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
			}else{
				request.setAttribute("product", bean);
				request.setAttribute("Insert", "新增成功");
				request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
			}
			break;
		case "Update":
			bean=pService.update(pb);
			if(bean==null){
				errors.put("Update", "修改失敗");
				request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
			}else{
				request.setAttribute("product", bean);
				request.setAttribute("UpdateSuccess", "修改成功");
				request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
			}
			
			break;
		case "Delete":
			boolean delete=pService.delete(pb);
			if(delete==false){
				errors.put("Delete", "刪除失敗");
				request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
			}else{
				request.setAttribute("UpdateSuccess", "修改成功");
				request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
			}
			break;
		case "Select" :
			List<ProductBean> beans=pService.select(pb);
			request.setAttribute("beans", beans);
			request.getRequestDispatcher("/pages/display.jsp").forward(request, response);
			break;
		}

	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
