package entity;

public class Ordering {

	//定义订单的编号
	private int orderingId;
	
	//定义顾客姓名属性
	private String userName;
	
	//定义菜名属性
	private String foodName;
	
	//定义订餐时间属性
	private int foodDeliveredTime;
	
	//定义送餐地址属性
	private String foodDeliveredAddress;
	
	//定义订单的总金额属性
	private double sumMoney;
	
	//定义订单状态属性
	private int orderingState;

	public int getOrderingId() {
		return orderingId;
	}

	public void setOrderingId(int orderingId) {
		this.orderingId = orderingId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodDeliveredTime() {
		return foodDeliveredTime;
	}

	public void setFoodDeliveredTime(int foodDeliveredTime) {
		this.foodDeliveredTime = foodDeliveredTime;
	}

	public String getFoodDeliveredAddress() {
		return foodDeliveredAddress;
	}

	public void setFoodDeliveredAddress(String foodDeliveredAddress) {
		this.foodDeliveredAddress = foodDeliveredAddress;
	}

	public double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
	}

	public int getOrderingState() {
		return orderingState;
	}

	public void setOrderingState(int orderingState) {
		this.orderingState = orderingState;
	}
	
}
