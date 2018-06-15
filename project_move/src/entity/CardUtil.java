package entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import service.CallService;
import service.NetService;
import service.SendService;

public class CardUtil {
	//创建map集合保存手机卡对象
	Map<String, MobileCard> card = new HashMap<String, MobileCard>();
	
	//创建map集合保存消费信息
	Map<String, List<ConsumInfo>> consumInfos = new HashMap<String, List<ConsumInfo>>();
	
	Scanner scanner= new Scanner(System.in);
	
	//创建集合保存场景信息
	Map<Integer, Scene> scenes = new HashMap<Integer, Scene>();
	
	//创建当前卡的消费的集合
	List<ConsumInfo> consum = new ArrayList<ConsumInfo>();
	
	//创建Scanner对象
	//Map<String, list<>>
	//生成一个卡号
	public String createNumber() {
		//定义
		StringBuffer buffer = new StringBuffer("139");
		
		boolean isExist = false;
		
		//定义变量变成生成的随机数
		int temp = 0;
		//
		do {
			temp = (int) (Math.random()*100000000);
			//System.out.println(temp);
			if(temp<10000000) {
				isExist = true;
			}else {
				buffer = buffer.append(temp);
				//遍历集合
				Set<String> set = card.keySet();
				for (String str : set) {
					if(str.equals(buffer.toString())) {
						isExist = true;
						break;
					}
				}
				break;
			}
		}while(isExist);
		return buffer.toString(); 
	}
	
	//生成9个卡号并展示出来
	public String[] getNewNumber() {
		//定义数组保存生成的卡号
		String[] strs = new String[9];
		for (int i = 0; i < strs.length; i++) {
			strs[i] = createNumber();
		}
		for (int i = 0; i < strs.length; i++) {
			if(i == 2 || i == 5 || i== 8) {
				System.out.print((i+1)+"."+strs[i]+"\n");
			}else {
				System.out.print((i+1)+"."+strs[i]+"\t");
			}
		}
		return strs;
	}
	//添加到集合中
	public void addCard() {
		//调用生成卡号的方法
		System.out.println("*********可选的卡号*********");
		String[] cards = getNewNumber();
		MobileCard mobileCard = new MobileCard();
		System.out.println("请输入1~9的序号");
		int index = scanner.nextInt();
		//把给对象的卡号属性赋值
		mobileCard.setCardNumber(cards[index-1]);
		//创建话痨套餐的对象
		TalkPackage talkPackage = new TalkPackage();
		System.out.print("1."+talkPackage.getName()+"\t");
		
		//创建网虫套餐的对象
		NetPackage netPackage = new NetPackage();
		System.out.print("2."+netPackage.getName()+"\t");
		
		//创建超人套餐的对象
		SuperPackage superPackage = new SuperPackage();
		System.out.print("3."+superPackage.getName()+"\t");
		System.out.print("请选择套餐(请输入序号):");
		int index2 = scanner.nextInt();
		
		
		System.out.println("请输入姓名:");
		//把输入的姓名赋值给对象中的用户名属性
		mobileCard.setUserName(scanner.next());
		System.out.println("请输入密码");
		//把输入的密码赋值给对象中的密码属性
		mobileCard.setPassword(scanner.next());
		System.out.println("请输入预存的话费金额");
		int money = scanner.nextInt();
		switch (index2) {
		case 1:
			while(true) {
				if(money<talkPackage.getPrice()) {
					System.out.println("您预付的话费金额不足以支付本月固定套餐资费，请重新充值");
					money = scanner.nextInt();
				}else {
					mobileCard.setMoney(money-talkPackage.getPrice());
					//消费金额为月资费
					mobileCard.setConsumAmount(talkPackage.getPrice());
					mobileCard.setSerPackage(talkPackage);
					break;
				}
			}
			break;
		case 2:
			while(true) {
				if(money<netPackage.getPrice()) {
					System.out.println("您预付的话费金额不足以支付本月固定套餐资费，请重新充值");
					money = scanner.nextInt();
					
				}else {
					//账户余额为充值的金额减去月资费并赋值给对象的账户余额属性
					mobileCard.setMoney(money-netPackage.getPrice());
					//消费金额为月资费
					mobileCard.setConsumAmount(netPackage.getPrice());
					mobileCard.setSerPackage(netPackage);
					break;
				}
			}
			
			break;
		case 3:
			while(true) {
				if(money<superPackage.getPrice()) {
					System.out.println("您预付的话费金额不足以支付本月固定套餐资费，请重新充值");
					money = scanner.nextInt();
				}else {
					//账户余额为充值的金额减去月资费并赋值给对象的账户余额属性
					mobileCard.setMoney(money-superPackage.getPrice());
					//消费金额为月资费
					mobileCard.setConsumAmount(superPackage.getPrice());
					mobileCard.setSerPackage(superPackage);
					break;
				}
			}
			break;
		}
		System.out.print("注册成功！");
		mobileCard.showMeg();
		//调用对应的套餐的showInfo(),展示套餐信息
		mobileCard.getSerPackage().showInfo();
		//把对象放在集合里面
		card.put(mobileCard.getCardNumber(), mobileCard);
		//System.out.println(card.size());
		
		
	}
	
	// 登录并得到当前卡号
	public String register() {
		System.out.println("请输入手机卡号");
		String moblieNumber = scanner.next();
		System.out.println("请输入密码");
		String password = scanner.next();
		//定义变量保存输入的
		String num = null;
		// 获得map集合的键集
		Set<String> set = card.keySet();

		if (set.size() == 0) {
			System.out.println("对不起，卡号不存在，请先注册(重新注册输入0)");
			num = scanner.next();
			if(num.equals("0")) {
				addCard();
			}else {
				System.out.println("请重新选择");
			}
		} else {
			// 遍历键的集合并且与输入的书籍卡号进行比对
			for (String string : set) {
				while (true) {
					if (string.equals(moblieNumber)) {
							// 密码也要进行比对 根据map的键得到值
							if (card.get(string).getPassword().equals(password)) {
								System.out.println("登录成功");
								return moblieNumber;
							} else {
								System.out.println("密码不正确，请重新输入");
								password = scanner.next();
							}
					} else {
						System.out.println("对不起，该卡号不存在，或卡号错误，请重新输入或者重新注册(重新注册输入0)");
						moblieNumber = scanner.next();
						System.out.println("请输入密码");
						password = scanner.next();
						if(moblieNumber.equals("0")) {
							addCard();
						}
					}
				}
			}
		}
		return null;

	}
 	
	//本月账单查询
	public void showAmountDetail(String moblieNumber) {
		//根据卡号的到元素的信息
		MobileCard mobileCard = card.get(moblieNumber);
		StringBuffer str = new StringBuffer();
		str.append("您的卡号:"+mobileCard.getCardNumber()+",当月账单:\n");
		str.append("资费套餐:"+mobileCard.getSerPackage().getPrice()+"元\n");
		str.append("合计:"+Common.dataFormat(mobileCard.getConsumAmount())+"元\n");
		str.append("账户余额:"+Common.dataFormat(mobileCard.getMoney())+"元");
		System.out.println(str.toString());
	}
	
	//套餐余量查询
	public void showRemainDetail(String moblieNumber) {
		//根据卡号的到元素的信息
		MobileCard mobileCard = card.get(moblieNumber);
		StringBuffer meg = new StringBuffer();
		//通话时长
		int remainTalkTime;
		//短信条数
		int remainSmsCount;
		//上网流量
		int remainFlow;
		meg.append("您的卡号是:"+moblieNumber+",套餐内剩余:\n");
		//得到套餐父类对象
		ServicePackage pack = mobileCard.getSerPackage();
		//话痨套餐
		if(pack instanceof TalkPackage) {
			TalkPackage talkPackage = (TalkPackage) pack;
			//计算剩余的通话时长
			remainTalkTime = talkPackage.getTalkTime() > mobileCard.getRealTalkTime() ? 
					talkPackage.getTalkTime() - mobileCard.getRealTalkTime() : 0;
			meg.append("通话时长:"+remainTalkTime+"分钟\n");
			//剩余的短信数
			remainSmsCount = talkPackage.getSmsCount() > mobileCard.getRealSMSCount() ?
					talkPackage.getSmsCount() - mobileCard.getRealSMSCount() : 0;
			meg.append("短信条数:"+remainSmsCount+"条");
		}else if(pack instanceof NetPackage) {
			NetPackage netPackage = (NetPackage) pack;
			//上网流量
			remainFlow = netPackage.getFlow() > mobileCard.getRealFlow() ?
					netPackage.getFlow() - mobileCard.getRealFlow() : 0;
			meg.append("上网流量:"+Common.dataFormat(remainFlow * 1.0 /1024)+"GB");
		} else if(pack instanceof SuperPackage) {
			SuperPackage SuperPackage = (SuperPackage) pack;
			//计算剩余的通话时长
			remainTalkTime = SuperPackage.getTalkTime() > mobileCard.getRealTalkTime() ? 
					SuperPackage.getTalkTime() - mobileCard.getRealTalkTime() : 0;
			meg.append("通话时长:"+remainTalkTime+"分钟\n");
			//剩余的短信数
			remainSmsCount = SuperPackage.getSmsCount() > mobileCard.getRealSMSCount() ?
					SuperPackage.getSmsCount() - mobileCard.getRealSMSCount() : 0;
			meg.append("短信条数:"+remainSmsCount+"条\n");
			//上网流量
			remainFlow = (SuperPackage.getFlow()) > mobileCard.getRealFlow() ?
					(SuperPackage.getFlow()) - mobileCard.getRealFlow() : 0;
			meg.append("上网流量:"+Common.dataFormat(remainFlow * 1.0 / 1024)+"GB");
		}
		System.out.println(meg);
	}
		
	// 打印消费详单
	public void printConsumInfo(String moblieNumber) {
		// 创建文件
		//存储指定卡号的消费记录
		File file = null;
		synchronized(this) {
			file = new File(moblieNumber+"消费记录.txt");
		}
		//创建写入字符流对象
		FileWriter fw = null;
		
		//创建带缓冲的字节流
		BufferedWriter bw =null;
		
		
		try {
			if (file.exists()) {
				file.delete();
				file.createNewFile();
			} else {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
			//判断消费信息集合中是否存在该卡的消费记录
			//获得消费信息集合中的键集
			Set<String> numbers = consumInfos.keySet();
			//生成迭代器
			Iterator<String> iterator = numbers.iterator();
			//该卡的消费集合
			//List<ConsumInfo> infos = consumInfos.get(moblieNumber);
			
			//判断是否存在该卡的消费信息
			boolean isExist = false;
			if(iterator.hasNext()) {
				String number = iterator.next();
				if(number.equals(moblieNumber)) {
					isExist = true;
				}
			}
			
			if(isExist) {
				StringBuffer content = new StringBuffer("*****"+moblieNumber+"消费记录*****\n");
				content.append("序号\t类型\t数据(通话(分钟)/上网(GB)/短信(条))\n");
				//循环添加消费信息到字符串中
				for (int i = 0; i < consum.size(); i++) {
					//实例化消费信息类
					//ConsumInfo info = new ConsumInfo();
					content.append((i+1)+".\t"+consum.get(i).getType()+"\t"+consum.get(i).getConsumData()+"\n");
				}
				//把字符写入到硬盘中
				bw.write(content.toString());
				bw.flush();
				System.out.println("消费信息打印成功");
			} else {
				System.out.println("对不起，不存在此号码的消费记录，不能打印");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	//变更套餐
	public void changingPack(String moblieNumber) {
		//获得当前卡的对象
		MobileCard mobileCard = card.get(moblieNumber);
		//获得当前卡号的套餐名称
		String packName = mobileCard.getSerPackage().getName();
		System.out.println("****套餐变更****");
		String[] packs = {"话痨套餐","网虫套餐","超人套餐"};
		for (int i = 0; i < packs.length; i++) {
			System.out.print((i+1)+"."+packs[i]+"\t");
		}
		//创建套餐父类对象
		ServicePackage  sp = null;
		System.out.println("请选择(序号):");
		
		int index = scanner.nextInt();
		switch(index) {
			case 1 :
				//创建话痨套餐的对象
				sp = new TalkPackage();
				break;
			case 2 :
				//创建网虫对象
				sp = new NetPackage();
				break;
			case 3 :
				//创建超人套餐的对象
				sp = new SuperPackage();
				break;
		}
		if(packName.equals(packs[index-1])) {
			System.out.println("对不起，您已经是该套餐用户，无需更换套餐");
		}else {
			if(mobileCard.getMoney()>sp.getPrice()) {
				mobileCard.setSerPackage(sp);
				//更换套餐后的账户余额
				mobileCard.setMoney(mobileCard.getMoney()-sp.getPrice());
				//更换套餐后的消费金额
				mobileCard.setConsumAmount(sp.getPrice());
				//当前卡实际使用数据清零
				mobileCard.setRealFlow(0);
				mobileCard.setRealSMSCount(0);
				mobileCard.setRealTalkTime(0);
				System.out.println("更换套餐成功");
				sp.showInfo();
			}else {
				System.out.println("您账号的余额不足，无法进行更换套餐，请充值后再办理更换套餐业务");
			}
		}
	}
	
	//办理退网
	public void delCard(String moblieNumber) {
		System.out.println("****办理退网****");
		//删除卡号集合中的信息
		card.remove(moblieNumber);
		//删除消费集合中的信息
		consumInfos.remove(moblieNumber);
		System.out.println("卡号"+moblieNumber+"办理退网成功！");
		System.out.println("谢谢使用！");
	}
	
	//初始化六个场景
	public void initScenes() {
		//创建场景信息对象
		Scene value1 = new Scene();
		value1.setType("通话");
		value1.setData(90);
		value1.setDescription("问候客户，谁知其如此难缠，通话90分钟");
		
		Scene value2 = new Scene();
		value2.setType("通话");
		value2.setData(30);
		value2.setDescription("询问妈妈身体状况，本地通话30分钟");
		
		Scene value3 = new Scene();
		value3.setType("短信");
		value3.setData(5);
		value3.setDescription("参与环境保护实施方案问卷调查，发送短信5条");
		
		Scene value4 = new Scene();
		value4.setType("短信");
		value4.setData(50);
		value4.setDescription("通知朋友手机换号，发送短信50条");
		
		Scene value5 = new Scene();
		value5.setType("上网");
		value5.setData(1024);
		value5.setDescription("和女友用微信视频聊天，使用流量1GB");
		
		Scene value6 = new Scene();
		value6.setType("上网");
		value6.setData(2048);
		value6.setDescription("晚上手机在线看韩剧，不留神睡着了，使用流量2GB");
		
		scenes.put(0, value1);
		scenes.put(1, value2);
		scenes.put(2, value3);
		scenes.put(3, value4);
		scenes.put(4, value5);
		scenes.put(5, value6);
	}
	
	//
	public void addConsumInfo(String number,String type,int data) {
		//实例化消费信息类
		ConsumInfo consumInfo = new ConsumInfo();
		consumInfo.setCardNumber(number);
		consumInfo.setType(type);
		consumInfo.setConsumData(data);
		
		//判断消费信息集合中是否存在该卡
		Set<String> card = consumInfos.keySet();
		//如果集合为空直接放进去
		if(consumInfos.size() == 0) {
			consum.add(consumInfo);
			consumInfos.put(number, consum);	
		}else {
			for (String str : card) {
				if(str.equals(number)) {
					//如果存在该卡号，就在该卡的消费信息集合里面增加
					consum.add(consumInfo);
					break;
				}else {
					consum.add(consumInfo);
					//如果不存在该卡，就把消费的信息放在map集合中
					consumInfos.put(number, consum);
				}
				
			}
		}
		System.out.println(consum.size());
		System.out.println(consumInfos.size());
		
	}
	
	//使用嗖嗖
	public void utilSoso() {
		//初始化场景信息
		initScenes();
		System.out.println("请输入手机卡号");
		String cardNumber = scanner.next();
		//遍历卡号集合
		//得到卡号的集合
		Set<String> set = card.keySet();
		//定义变量记录各场景中的实际消费数据
		int temp = 0;
		//定义循环条件
		boolean isTrue = true;
		//获得此卡对象
		MobileCard mobileCard = card.get(cardNumber);
		if(set.size() == 0) {
			System.out.println("对不起，请先注册");
			return;
		}
		for (String string : set) {
			if(string.equals(cardNumber)) {
				//获取此卡的所属套餐
				ServicePackage servicePackage = mobileCard.getSerPackage();
				do {
					//生成0-5的随机数
					int nums = new Random().nextInt(6);
					//获取该序号的所对应的场景
					Scene scene = scenes.get(nums);
					switch (nums) {
					case 0:
					case 1:
						if(servicePackage instanceof CallService) {
							//显示通话的方法
							System.out.println(scene.getDescription());
							CallService callService = (CallService)servicePackage;
							//给实际通话时长赋值
							try {
								temp = callService.call(scene.getData(), mobileCard);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							//添加一条消费记录
							addConsumInfo(cardNumber, scene.getType(), temp);
							System.out.println("不存在此卡的该条消费信息，已添加一条消费信息");
							isTrue = false;
						}else {
							//如果该卡套餐所不支持通话功能则重新生成随机数选择其他场景
							continue;
						}
						break;
					case 2:
					case 3:
						if(servicePackage instanceof SendService) {
							//显示发送短信的场景
							System.out.println(scene.getDescription());
							SendService sendService = (SendService)servicePackage;
							//给实际上网流量赋值
							try {
								temp = sendService.send(scene.getData(), mobileCard);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							//添加一条消费记录
							addConsumInfo(cardNumber, scene.getType(), temp);
							System.out.println("不存在此卡的该条消费信息，已添加一条消费信息");
							isTrue = false;
						}else {
							//如果该卡套餐所不支持通话功能则重新生成随机数选择其他场景
							continue;
						}
						break;
					case 4:
					case 5:
						if(servicePackage instanceof NetPackage) {
							//显示上网的方法
							System.out.println(scene.getDescription());
							NetService netService = (NetService)servicePackage;
							//给实际上网流量赋值
							try {
								temp = netService.flow(scene.getData(),mobileCard);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							//添加一条消费记录
							addConsumInfo(cardNumber, scene.getType(), temp);
							System.out.println("不存在此卡的该条消费信息，已添加一条消费信息");
							isTrue = false;
						}else {
							//如果该卡套餐所不支持通话功能则重新生成随机数选择其他场景
							continue;
						}
						break;
					}
				} while(isTrue);
			}
		}
	}
	
	//话费充值
	public void chargeMoney() {
		System.out.println("请输入要充值的卡号");
		String number = scanner.next();
		//得到卡号
		Set<String> numbers = card.keySet();
		if(numbers.size() == 0) {
			System.out.println("对不起，请先注册");
			return;
		}else {
			//遍历每个卡号
			for (String cards : numbers) {
				if(cards.equals(number)) {
					System.out.println("请输入充值金额");
					int money = scanner.nextInt();
					if(money < 50) {
						System.out.println("对不起，充值金额最少为50元");
					}else {
						//根据卡号修改对应的账户余额
						MobileCard mobileCard = card.get(number);
						mobileCard.setMoney(money+mobileCard.getMoney());
						System.out.println("充值成功，当前话费余额为"+Common.dataFormat(mobileCard.getMoney())+"元。");
					}
				}else {
					System.out.println("您的号卡输入有误，或不存在，请重新选择");
					return;
				}
			}
		}
	}
	
	//资费说明
	public void showDescription() {
		//创建文件对象
		File file = new File("D:\\WorkSpace_g\\套餐资费说明.txt");
		//创建字符输入流对象
		FileReader fr = null;
		
		//创建带缓冲的字符输入流对象	
		BufferedReader br = null;
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String str = null;
			while((str = br.readLine()) != null ) {
				System.out.println(str);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
