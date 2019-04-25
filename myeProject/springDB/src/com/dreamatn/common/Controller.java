package com.dreamatn.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dreamatn.dao.MyDataSource;

public class Controller {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException {

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MyDataSource mds = (MyDataSource) ac.getBean("dataSource");
		String driverClass = mds.getDriverClass();
		String jdbcUrl = mds.getJdbcUrl();
		String user = mds.getUser();
		String password = mds.getPassword();
		Connection conn = null; 
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from dev001.citbas";

		try {
			Class.forName(driverClass).newInstance();
			System.out.println("driverclass");
			conn = DriverManager.getConnection(jdbcUrl, user,
					password);
			System.out.println("connect success");
			st =  conn.createStatement();
			rs =  st.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("ci_no"));
				System.out.println(rs.getString("ci_typ"));
				System.out.println(rs.getString("ci_attr"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("connect fail");
		} finally{
			rs.close();
			st.close();
			conn.close();
		}
	}
}
