package test;

import java.util.Scanner;

import entity.CardUtil;


//主页
public class SosoMgr {

	Scanner scanner = new Scanner(System.in);
	
	CardUtil cardUtil = new CardUtil();
	
	public static void main(String[] args) {
		SosoMgr soso = new SosoMgr();
		soso.mainMenu();
	}
	
	//主菜单
	public void mainMenu() {
		
		// 创建Scanner对象
		do {
			System.out.println("**************欢迎使用嗖嗖移动大厅**************");
			System.out.println("1.用户登录\t2.用户注册\t3.使用嗖嗖\t4.话费充值\t5.资费说明\t6.退出系统");
			System.out.println("请选择:");
			// 定义变量保存输入的序号
			int nums = scanner.nextInt();
			//定义变量接收卡号
			String cards = null;
			// 判断选择的序号，以及实现对应的功能
			switch (nums) {
			case 1:
				cards = cardUtil.register();
				if(cards != null) {
					cardMenu(cards);
				}
				break;
			case 2:
				cardUtil.addCard();
				break;
			case 3:
				cardUtil.utilSoso();
				break;
			case 4:
				cardUtil.chargeMoney();
				break;
			case 5:
				cardUtil.showDescription();
				break;
			case 6:
				System.out.println("感谢使用嗖嗖移动业务大厅程序");
				return;
			default:
				System.out.println("对不起，您输入有误，请重新输入！");
			}
		}while(true);
	}
	
	//二级菜单
	public void cardMenu(String card) {
		do {
			System.out.println("*****嗖嗖移动用户菜单*****");
			System.out.println("1.本月账单查询\t2.套餐余量查询\t3.打印消费详单\t4.套餐变更\t5.办理退网");
			System.out.println("请选择(输入1-5选择功能，其他键返回上一级):");
			// 定义变量保存输入的序号
			int nums = scanner.nextInt();
			// 判断选择的序号，以及实现对应的功能
			switch (nums) {
			case 1:
				//调用工具类中的方法并把对应的卡号传过去
				cardUtil.showAmountDetail(card);
				break;
			case 2:
				cardUtil.showRemainDetail(card);
				break;
			case 3:
				cardUtil.printConsumInfo(card);
				break;
			case 4:
				cardUtil.changingPack(card);
				break;
			case 5:
				cardUtil.delCard(card);
				return;
			default:
				System.out.println("对不起，您输入有误，返回上一级菜单");
				//mainMenu();
				return;
			}
		}while(true);
	}
}
