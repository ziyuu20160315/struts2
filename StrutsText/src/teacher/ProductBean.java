package teacher;

public class ProductBean {
	
	private  int id;
	private  String name;
	private  double price;
	private  java.util.Date make;
	private  int expire;
	
	
	
	@Override
	public String toString() {
		return "ProductBean [id=" + id + ",\n	 name=" + name + ",	 \nprice=" + price + ",\n	 make=" + make + ",\n	 expire=" + expire
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public java.util.Date getMake() {
		return make;
	}
	public void setMake(java.util.Date make) {
		this.make = make;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
	
	
}
