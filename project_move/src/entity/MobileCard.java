package entity;

import java.io.Serializable;

//卡号类
public class MobileCard implements Serializable{

	
	private static final long serialVersionUID = 1L;

	//卡号
	private String cardNumber;
	
	//用户名
	private String userName;
	
	//密码 
	private String password;
	
	//所属套餐
	private ServicePackage serPackage;
	
	//当月消费金额
	private double consumAmount;
	
	//账户余额
	private double money;
	
	//当月实际通话时长
	private int realTalkTime;
	
	//当月实际发送短信条数
	private int realSMSCount;
	
	//当月实际上网流量
	private int realFlow;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ServicePackage getSerPackage() {
		return serPackage;
	}

	public void setSerPackage(ServicePackage serPackage) {
		this.serPackage = serPackage;
	}

	public double getConsumAmount() {
		return consumAmount;
	}

	public void setConsumAmount(double consumAmount) {
		this.consumAmount = consumAmount;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getRealTalkTime() {
		return realTalkTime;
	}

	public void setRealTalkTime(int realTalkTime) {
		this.realTalkTime = realTalkTime;
	}

	public int getRealSMSCount() {
		return realSMSCount;
	}

	public void setRealSMSCount(int realSMSCount) {
		this.realSMSCount = realSMSCount;
	}

	public int getRealFlow() {
		return realFlow;
	}

	public void setRealFlow(int realFlow) {
		this.realFlow = realFlow;
	}
	
	public void showMeg() {
		System.out.println("卡号:"+cardNumber+"\t用户名:"+userName+"\t当前余额"+money);
	}
	
}
