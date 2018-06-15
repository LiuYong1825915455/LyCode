package entity;

//定义菜单类
public class Menu {

	//定义菜单的序号
	private int greensId;
	
	//定义菜的名称
	private String greensName;
	
	//定义菜的价格
	private double greensPrice;
	
	//定义菜单的点赞数
	private int praises;

	//创建set  get方法
	public int getGreensId() {
		return greensId;
	}

	public void setGreensId(int greensId) {
		this.greensId = greensId;
	}

	public String getGreensName() {
		return greensName;
	}

	public void setGreensName(String greensName) {
		this.greensName = greensName;
	}

	public double getGreensPrice() {
		return greensPrice;
	}

	public void setGreensPrice(double greensPrice) {
		this.greensPrice = greensPrice;
	}

	public int getPraises() {
		return praises;
	}

	public void setPraises(int praises) {
		this.praises = praises;
	}
	
	
}
