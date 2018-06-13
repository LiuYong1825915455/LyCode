package entity;

import java.io.Serializable;

//套餐类
public class ServicePackage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//套餐名称
	private String name;

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	//资费
	private double price;
	


	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public void showInfo() {
		
	}



	
}
