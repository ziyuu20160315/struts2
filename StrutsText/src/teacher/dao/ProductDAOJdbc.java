package teacher.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import teacher.ProductBean;
import teacher.ProductDAO;
@Component
public class ProductDAOJdbc implements ProductDAO {
//	private static final String URL = "jdbc:sqlserver://localhost:1433;database=java";
//	private static final String USERNAME = "sa";
//	private static final String PASSWORD = "sa123456";
	
	private static final String SELECT_BY_ID = "select * from product where id=?";	
	/* (non-Javadoc)
	 * @see model.dao.ProductDAO#select(int)
	 */
	@Autowired
	private DataSource datasource;
	public void setDataSource(DataSource datasource){
		this.datasource=datasource;
	}
	
	
	@Override
	public ProductBean select(int id) {
		ProductBean result = null;				
		ResultSet rs = null;
		try(//Connection conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Connection conn	=datasource.getConnection();
		     PreparedStatement ps=conn.prepareStatement(SELECT_BY_ID);) {
			
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if (rs.next()){
				result =new ProductBean();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setPrice(rs.getDouble("price"));
				result.setMake(rs.getTime("make"));
				result.setExpire(rs.getInt("expire"));
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
		return result;		
	}
	
	
	private static final String SELECT_ALL = "select * from product";
	/* (non-Javadoc)
	 * @see model.dao.ProductDAO#select()
	 */
	@Override
	public List<ProductBean> select() {
		List<ProductBean> listPB = null;
		ProductBean result = null;	
		
		ResultSet rs = null;
		try(//Connection conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Connection conn	=datasource.getConnection();
			     PreparedStatement ps=conn.prepareStatement(SELECT_ALL);) {
				rs = ps.executeQuery();
				listPB=new ArrayList<>();
				while (rs.next()){		
					result =new ProductBean();
					result.setId(rs.getInt("id"));
					result.setName(rs.getString("name"));
					result.setPrice(rs.getDouble("price"));
					result.setMake(rs.getTime("Make"));
					result.setExpire(rs.getInt("expire"));
					listPB.add(result);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if (rs !=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		
		
		return listPB;
	}
	
	
	
	
	
	
	private static final String UPDATE =
			"update product set name=?, price=?, make=?, expire=? where id=?";
	/* (non-Javadoc)
	 * @see model.dao.ProductDAO#update(java.lang.String, double, java.util.Date, int, int)
	 */
	@Override
	public ProductBean update(String name,
			double price, java.util.Date make, int expire, int id) {
		ProductBean result = null;
		
		try(//Connection conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Connection conn	=datasource.getConnection();
			     PreparedStatement ps=conn.prepareStatement(UPDATE);) {
         		ps.setString(1,name);
         		ps.setDouble(2, price);
         		ps.setDate(3, new java.sql.Date(make.getTime()));
         		ps.setInt(4, expire);
         		ps.setInt(5, id);
         		ps.executeUpdate();
         		result =new ProductBean();
         		result.setId(id);
         		result.setName(name);
         		result.setPrice(price);
         		result.setMake(make);
         		result.setExpire(expire);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		return result;
	}
	
	
	
	private static final String INSERT =
			"insert into product (id, name, price, make, expire) values (?, ?, ?, ?, ?)";
	
	/* (non-Javadoc)
	 * @see model.dao.ProductDAO#insert(model.ProductBean)
	 */
	@Override
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		
		try(//Connection conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Connection conn	=datasource.getConnection();
			     PreparedStatement ps=conn.prepareStatement(INSERT);) {
				if(bean != null){
			
				ps.setInt(1,bean.getId());
				ps.setString(2, bean.getName());
				ps.setDouble(3, bean.getPrice());
				ps.setDate(4, new java.sql.Date(bean.getMake().getTime()));
				ps.setInt(5, bean.getExpire());
				int i=ps.executeUpdate();
				
				if (i==1){
					result = new ProductBean();
					result.setId(bean.getId());
					result.setName(bean.getName());
					result.setPrice(bean.getPrice());
					result.setMake(bean.getMake());
					result.setExpire(bean.getExpire());
				}
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;	
	}
	
	private static final String DELETE = "delete from product where id=?";
	/* (non-Javadoc)
	 * @see model.dao.ProductDAO#delete(int)
	 */
	@Override
	public int delete(int id) {
		
		int i=0;
		try(//Connection conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Connection conn	=datasource.getConnection();
				PreparedStatement ps=conn.prepareStatement(DELETE);) {			
			ps.setInt(1, id);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return i;
	}
}
