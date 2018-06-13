package entity;

import service.CallService;
import service.SendService;

//话痨套餐
public class TalkPackage extends ServicePackage implements CallService,SendService{

	private static final long serialVersionUID = 1L;
	
	//通话时长
	private int talkTime = 500;
	//短信条数
	private int smsCount = 30;
	
	public TalkPackage() {
		setPrice(58);
		setName("话痨套餐");
	}


	public int getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
	}

	public int getSmsCount() {
		return smsCount;
	}

	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}

	@Override
	public void showInfo() {
		System.out.println("套餐类型:"+this.getName());
		System.out.println("通话时长:"+talkTime+"分钟");
		System.out.println("短信条数:"+smsCount+"条");
		System.out.println("月资费:"+this.getPrice()+"元");
	}


	@Override
	public int send(int count, MobileCard card) throws Exception {
		//接收实际的发送短信条数
		int temp = count;
		for (int i = 0; i < temp; i++) {
			if(smsCount > card.getRealSMSCount()) {
				//当套餐的短信条数大于实际发送条数，实际条数加1
				card.setRealSMSCount(card.getRealSMSCount() + 1);
			} else if(card.getMoney() >= 0.1) {
				//当套餐的短信条数大于实际发送条数，实际条数加1
				card.setRealSMSCount(card.getRealSMSCount() + 1);
				//账户余额
				card.setMoney(Common.sub(card.getMoney(), 0.1));
				//当月消费金额
				card.setConsumAmount(card.getConsumAmount() + 0.1);
			} else {
				//记录实际发送短信数
				temp = i;
				throw new Exception("本次发送短信"+i+"条，您的余额不足，请充值后再使用");
			}
		}
		
		return temp;
	}


	@Override
	public int call(int minCount, MobileCard card) throws Exception {
		//接收实际的通话时长
		int temp = minCount;
		//把时间分为每分钟
		for (int i = 0; i < temp; i++) {
			if(talkTime > card.getRealTalkTime()) {
				//当套餐类的通话时长大于实际通话时长是，实际通话时长加1
				card.setRealTalkTime(card.getRealTalkTime()+1);
			}else if( card.getMoney() >= 0.2) {
				//实际通话时长加1
				card.setRealTalkTime(card.getRealTalkTime()+1);
				//账户余额
				card.setMoney(Common.sub(card.getMoney(), 0.2));
				//当月消费金额
				card.setConsumAmount(card.getConsumAmount() + 0.2);
			}else {
				//记录实际通话分钟
				temp = i;
				throw new Exception("本次通话"+i+"分钟，您的余额不足，请充值后再使用");
			}
		}
		return temp;
	}


	

}
