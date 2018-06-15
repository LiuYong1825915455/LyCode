package test;

import java.util.Scanner;

import entity.OrderingMar;



public class HomePage {

	public static void main(String[] args) {
		//实例对象
		OrderingMar ordering = new OrderingMar();
		//创建Scanner对象
		Scanner scanner = new Scanner(System.in);
		System.out.println("欢迎使用“吃货联盟订餐系统”");
		//定义循环条件
		boolean isExit = false;
		//定义变量保存选择的序号
		int num = 0;
		//定义变量保存数组下标
		int index = -1;
		do {
			System.out.println("****************************");
			System.out.println();
			System.out.println("1、我要订餐");
			System.out.println("2、查看餐袋");
			System.out.println("3、签收订单");
			System.out.println("4、删除订单");
			System.out.println("5、我要点赞");
			System.out.println("6、退出系统");
			System.out.println("****************************");
			System.out.println("请选择(1-6)");
			//定义变量保存选择的序号
 			num = scanner.nextInt();
			switch(num) {
			//选择1时
				case 1:
					index++;
					ordering.order();
					System.out.println("输入0返回");
					num = scanner.nextInt();
					if(num == 0) {
						isExit = true;
					}
					break;
			//选择2时
				case 2:
					ordering.select();
					System.out.println("输入0返回");
					num = scanner.nextInt();
					if(num == 0) {
						isExit = true;
					}
					break;
			//选择3时
				case 3:
					ordering.signFor();
					System.out.println("输入0返回");
					num = scanner.nextInt();
					if(num == 0) {
						isExit = true;
					}
					break;
			//选择4时
				case 4:
					ordering.delete();
					System.out.println("输入0返回");
					num = scanner.nextInt();
					if(num == 0) {
						isExit = true;
					}
					break;
			//选择5时
				case 5:
					ordering.praise();
					System.out.println("输入0返回");
					num = scanner.nextInt();
					if(num == 0) {
						isExit = true;
					}
					break;
			//选择6时
				case 6:
					System.out.println("**退出系统**");
					System.out.println("谢谢使用，欢迎下次光临");
					return;
				default :
					System.out.println("你输入的有误，请重新选择(1-6)");
					isExit = true;
					break;
			}
		}while(isExit);
	}

}
