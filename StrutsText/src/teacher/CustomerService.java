package teacher;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
@Component(value="customerService")
public class CustomerService {
	@Autowired
	private CustomerDAO customerDao;

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}
	
	



	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		
		
		CustomerService cs =(CustomerService) context.getBean("customerService");
		
		System.out.println(cs.login("ellen", "E"));
	//	System.out.println(cs.changePassword("alex", "EEE", "A"));
		
		((ConfigurableApplicationContext)context).close();
	}
	
	
	
	
	
	public boolean changePassword(String userName,String oldPassword ,String newPassword){
		CustomerBean bean =this.login(userName, oldPassword);
		if(bean !=null){
			if(newPassword !=null && newPassword.trim().length() !=0){
				byte[] temp = newPassword.getBytes();
				return customerDao.update( temp, bean.getEmail(), bean.getBirth(),userName);
			}
			
		}

		
		return false;
	}
	
	
	public CustomerBean login(String userName,String password)
	{
		CustomerBean bean = customerDao.select(userName);
		if(bean !=null){
			if(password != null && password.trim().length() !=0){
				byte[] pass = bean.getPassword();
				byte[] temp =password.getBytes();
				if(Arrays.equals(pass, temp)){
					return bean;
				}
			}
		}
		
		return null;
	}
	
	
	
	
	
	
	
	

}
