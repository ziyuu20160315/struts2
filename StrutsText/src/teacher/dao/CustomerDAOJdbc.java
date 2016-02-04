package teacher.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import teacher.CustomerBean;
import teacher.CustomerDAO;
@Component
public class CustomerDAOJdbc implements CustomerDAO {
	
//	private static final String URL="jdbc:sqlserver://localhost:1433;database=java";
//	private static final String USERID="sa";
//	private static final String PASSWORD = "sa123456";
	@Autowired
	private DataSource datasource;
	public void setDataSource(DataSource datasource){
		this.datasource=datasource;
	}
	
	public static void main(String[] args){
		CustomerDAO test=new CustomerDAOJdbc();
		CustomerBean cb =test.select("alex");
		System.out.println(cb);
		
		test.update( "EEE".getBytes(), "AAA@AAA", new java.util.Date(),"Alex");
		
	}
	
	
	private static final String SELECT="select custid,password,email,birth from customer where custid=?";
	/* (non-Javadoc)
	 * @see model.dao.CustomerDAO#select(java.lang.String)
	 */
	@Override
	public CustomerBean select(String custid){
		CustomerBean cb = null;		

		ResultSet rs=null;
		try(//Connection conn = DriverManager.getConnection(URL, USERID, PASSWORD);
		    Connection conn	=datasource.getConnection();
			PreparedStatement	ps = conn.prepareStatement(SELECT);) {
		
			ps.setString(1, custid);
			rs = ps.executeQuery();
			
			if(rs.next()){
				cb=new CustomerBean();
				cb.setCustid(rs.getString(1));
				cb.setPassword(rs.getBytes(2));
				cb.setEmail(rs.getString(3));
				cb.setBirth(rs.getDate(4));
			}
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}finally{
			if (rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
		
					e.printStackTrace();
				} 
			}
		}
		
	
		return cb;
	}
	
	private static final String UPDATE=
			  "update customer set password=? ,email=?,birth=? where custid=? ";
	
	/* (non-Javadoc)
	 * @see model.dao.CustomerDAO#update(byte[], java.lang.String, java.util.Date, java.lang.String)
	 */
	@Override
	public boolean update(byte[] password,String email,java.util.Date birth,String custid){
		
		Connection conn=null;
		PreparedStatement ps=null;
		int i=0;
		try {
			//conn = DriverManager.getConnection(URL, USERID, PASSWORD);
			conn	=datasource.getConnection();
			ps = conn.prepareStatement(UPDATE);
			ps.setBytes(1, password);
			ps.setString(2, email);
			ps.setDate(3, new java.sql.Date(birth.getTime()));
			ps.setString(4, custid);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			if (ps !=null) {
				try {
					ps.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				} 
			}
			if (conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
			
					e.printStackTrace();
				} 
			}
		}
		
		if (i>0){
		return true;
		}else{
			return false;
		}
	}
	
	
}
