package entity;

import java.text.DecimalFormat;

public class Common {

	//规定格式
	public static String dataFormat(double data) {
		DecimalFormat format = new DecimalFormat("#.0");
		return format.format(data);
	}
	
	public static double sub(double money,double price) {
		//账户余额能够打多少分钟的电话
		if(money > price) {
			money = money - price;
			return money;
		}
		return 0;
	}
}
