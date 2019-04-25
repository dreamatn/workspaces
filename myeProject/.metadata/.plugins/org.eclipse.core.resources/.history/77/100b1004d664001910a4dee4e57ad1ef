package com.city.j2ee.ch02;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ShowEmployeeList extends HttpServlet {
	private Connection cn = null;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		String driver = config.getInitParameter("driver");
		String url = config.getInitParameter("url");
		String user = config.getInitParameter("user");
		String password = config.getInitParameter("password");
		try {
			Class.forName(driver);
			cn = DriverManager.getConnection(url, user, password);
		} catch (Exception e)
		{
			System.out.println("Init Error:" + e.getMessage());
		}
	}
	/**
	 * 
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		try {
			if (cn != null && (!cn.isClosed()))
			{
				cn.close();
				cn = null;
			}
		} catch (Exception e) {
			System.out.println("Destroy Error:" + e.getMessage());
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("<BODY>");
		try {
			String sql = "select ename,job,sal,hiredate from emp";
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			out.print("<h1>员工列表</h1>");
			out.print("<table border='1'>");
			out.print("<tr>");
			out.print("<td>姓名</td><td>职位</td><td>工资</td><td>加入公司日期</td>");
			out.print("</tr>");
			while (rs.next()) {
				out.print("<tr>");
				out.println("<td>" + rs.getString("ENAME") + "</td>");
				out.println("<td>" + rs.getString("JOB") + "</td>");
				out.println("<td>" + rs.getString("sal") + "</td>");
				out.println("<td>" + rs.getString("hiredate") + "</td>");
				out.println("</tr>");
			}
			rs.close();
			ps.close();
			out.print("</table>");
		} catch (Exception e) {
			out.println("<h2>处理请求发生错误：" + e.getMessage() + "</h2>");
		}
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}