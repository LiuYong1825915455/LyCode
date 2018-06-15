package entity;

import java.util.Scanner;

public class OrderingMar {

	//创建Scanner对象
	Scanner scanner = new Scanner(System.in);
	//定义静态的订单数组
	static Ordering[] ordering = new Ordering[4];
	//定义静态菜单的数组，保存菜品信息
	static Menu[] menus = new Menu[3];
	//定义一个静态块
	static{
		//创建第一个菜品对象
		menus[0] =  new Menu();
		menus[0].setGreensId(1);
		menus[0].setGreensName("红烧带鱼");
		menus[0].setGreensPrice(38);
		menus[0].setPraises(0);
		//创建第二个菜品对象
		menus[1] =  new Menu();
		menus[1].setGreensId(2);
		menus[1].setGreensName("鱼香肉丝");
		menus[1].setGreensPrice(20);
		menus[1].setPraises(0);
		//创建第三个菜品对象
		menus[2] =  new Menu();
		menus[2].setGreensId(3);
		menus[2].setGreensName("时令蔬菜");
		menus[2].setGreensPrice(10);
		menus[2].setPraises(0);
		
	}
	// 定义静态的订单的数组，最多能保存4条订单

	// 下订单
	public void order() {
		System.out.println("**我要订餐**");
		System.out.print("请输入订餐人的姓名:");
		//定义变量保存数组的下标
		int num = 0;
		//定义循环条件
		boolean flag = false;
		for (int i = 0; i < OrderingMar.ordering.length; i++) {
			if (OrderingMar.ordering[i] == null) {
				OrderingMar.ordering[i] = new Ordering();
				OrderingMar.ordering[i].setOrderingId(i + 1);
				OrderingMar.ordering[i].setUserName(scanner.next());
				num = i;
				break;
			}
		}
		//遍历菜单数组
		System.out.println("序号\t\t\t\t菜名\t\t\t\t单价\t\t\t\t点赞数");
		for (int i = 0; i < OrderingMar.menus.length; i++) {
			System.out.println(OrderingMar.menus[i].getGreensId() + "\t\t\t\t" + OrderingMar.menus[i].getGreensName() + "\t\t\t\t"
					+ OrderingMar.menus[i].getGreensPrice() + "\t\t\t\t" + OrderingMar.menus[i].getPraises());
		}
		System.out.println("请输入您要点的菜品编号");
		int id = scanner.nextInt();
		System.out.println("请输入点的份数");
		// 定义变量保存份数
		int copies = scanner.nextInt();
		System.out.println("请输入送餐时间(送餐时间是10点至20点间整数点)");
		// 定义变量保存送餐时间
		int time = scanner.nextInt();
		do{
			if(time>=10 && time <=20) {
				// 在数组中保存送餐时间
				OrderingMar.ordering[num].setFoodDeliveredTime(time);
				flag = false;
			}else {
				flag = true;
				System.out.println("请重新输入送餐时间");
				time = scanner.nextInt();
			}
		}while(flag);

		System.out.println("请输入送餐地址");
		// 定义变量保存送餐地址
		// 在数组中保存送餐地址
		OrderingMar.ordering[num].setFoodDeliveredAddress(scanner.next());
		// 定义获取变量获取菜名
		String name = "";
		// 定义变量获取价格
		double price = 0;
		for (int i = 0; i < OrderingMar.menus.length; i++) {
			if (OrderingMar.menus[i].getGreensId() == id) {
				name = (OrderingMar.menus[i].getGreensName() + copies + "份");
				price = OrderingMar.menus[i].getGreensPrice();
				System.out.println("您订的是" + name);
			}
		}
		// 把菜名放在数组中
		OrderingMar.ordering[num].setFoodName(name);
		// 定义运费变量
		double foodDeliveredMoney = (price * copies) >= 50 ? 0 : 5;
		// 定义总金额变量
		double sum = foodDeliveredMoney + (price * copies);
		// 把价格放在数组中
		OrderingMar.ordering[num].setSumMoney(sum);
		System.out.println("餐费:" + (price * copies) + "元，送餐费:" + foodDeliveredMoney + "元，总计:" + sum + "元");
		OrderingMar.ordering[num].setOrderingState(0);
		System.out.println("预定成功");
	}
	
	//查看餐袋
	public void select() {
		System.out.println("查看餐袋");
		//判断有没有订单
		if(OrderingMar.ordering[0] == null) {
			System.out.println("您还没有订餐，没有您的餐袋信息");
		}else {
			System.out.println("序号\t\t订单人\t\t餐品信息\t\t送餐时间\t\t送餐地址\t\t总金额\t\t订单状态");
			for (int i = 0; i < OrderingMar.ordering.length; i++) {
				if(OrderingMar.ordering[i] != null && OrderingMar.ordering[i].getOrderingState() == 0) {
					System.out.println(OrderingMar.ordering[i].getOrderingId() + "\t\t" + OrderingMar.ordering[i].getUserName() + "\t\t" + OrderingMar.ordering[i].getFoodName() + "\t\t"
							+ OrderingMar.ordering[i].getFoodDeliveredTime() + "\t\t" + OrderingMar.ordering[i].getFoodDeliveredAddress() + "\t\t"+OrderingMar.ordering[i].getSumMoney()+"\t\t"
							+ "已预订" );
				}else if(OrderingMar.ordering[i] != null && OrderingMar.ordering[i].getOrderingState() == 1) {
					System.out.println(OrderingMar.ordering[i].getOrderingId() + "\t\t" + OrderingMar.ordering[i].getUserName() + "\t\t" + OrderingMar.ordering[i].getFoodName() + "\t\t"
							+ OrderingMar.ordering[i].getFoodDeliveredTime() + "\t\t" + OrderingMar.ordering[i].getFoodDeliveredAddress() + "\t\t"+OrderingMar.ordering[i].getSumMoney()+"\t\t"
							+ "已完成" );
				}
			}
		}
	}
	
	//签收订单
	public void signFor() {
		System.out.println("请选择要签收的订单序号");
		//定义变量保存要签收的订单序号
		int num = scanner.nextInt();
		if (OrderingMar.ordering[0] == null) {
			System.out.println("您还没有下单，请重新选择");
		}
		for (int i = 0; i < OrderingMar.ordering.length; i++) {
			if(OrderingMar.ordering[i].getOrderingId() == num) {
				OrderingMar.ordering[i].setOrderingState(1);
				System.out.println("订单签收完成");
				break;
			}
			
		}
	}
	
	//删除订单
	public void delete() {
		//判断订单中的orderingState属性是否是已完成
		System.out.println("请输入要删除的订单序号");
		//定义变量保存要删除的订单序号
		int num1 = scanner.nextInt();
		if(OrderingMar.ordering[num1-1].getOrderingState() == 0) {
			System.out.println("你选择的订单未签收，不能删除！");
		}else if(OrderingMar.ordering[num1-1].getOrderingState() == 1) {
			/*
			 * 判断要删除的订单序号
			 * 先要判断数组里面有几个订单
			 * 
			 */
			//定义变量保存数组中订单的个数
			for (int i = 0; i < OrderingMar.ordering.length; i++) {
				if(OrderingMar.ordering[i].getOrderingId() == num1) {
					if(num1 == OrderingMar.ordering.length) {
						OrderingMar.ordering[i] = null;
					}else {
						for (int j = i; j <OrderingMar.ordering.length-1 ; j++) {
							OrderingMar.ordering[i+j] = OrderingMar.ordering[i+j+1];
							OrderingMar.ordering[OrderingMar.ordering.length-1] = null;
						}
					}
					break;
				}
			}
			System.out.println("删除成功");
		}
	}

	//我要点赞
	public void praise() {
		System.out.println("序号\t\t\t\t菜名\t\t\t\t单价\t\t\t\t点赞数");
		for (int i = 0; i < OrderingMar.menus.length; i++) {
			System.out.println(OrderingMar.menus[i].getGreensId() + "\t\t\t\t" + OrderingMar.menus[i].getGreensName() + "\t\t\t\t"
					+ OrderingMar.menus[i].getGreensPrice() + "\t\t\t\t" + OrderingMar.menus[i].getPraises());
		}
		System.out.println("请输入您要点赞的菜品序号");
		//定义变量保存输入的菜品序号
		int numbers = scanner.nextInt();
		//定义变量保存点赞数
		int praise = 0;
		for (int i = 0; i < OrderingMar.menus.length; i++) {
			if(OrderingMar.menus[i].getGreensId() == numbers) {
				praise++;
				OrderingMar.menus[numbers].setPraises(praise);
				break;
			}
		}
		System.out.println("点赞成功");
	}
}
