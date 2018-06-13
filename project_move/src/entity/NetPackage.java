package entity;

import service.NetService;

//网虫套餐
public class NetPackage extends ServicePackage implements NetService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NetPackage() {
		this.setPrice(68);
		this.setName("网虫套餐");
	}
	
	
	//上网流量
	private int flow = 3072;
	

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}


	public void showInfo() {
		
		System.out.println("套餐类型:"+this.getName());
		System.out.println("上网流量:"+Common.dataFormat((flow * 1.0) / 1024)+"GB");
		System.out.println("月资费:"+this.getPrice()+"元");
	}

	@Override
	public int flow(int flow, MobileCard card) throws Exception {
		//接收上网流量
		int temp = flow;
		for (int i = 0; i < temp; i++) {
			if(flow > card.getRealFlow()) {
				//当套餐类的上网流量大于实际上网流量，上网流量加1
				card.setRealFlow(card.getRealFlow()+1);
			}else if( card.getMoney() >= 0.1) {
				//实际上网流量加1
				card.setRealFlow(card.getRealFlow()+1);
				//账户余额
				card.setMoney(Common.sub(card.getMoney(), 0.1));
				//当月消费金额
				card.setConsumAmount(card.getConsumAmount() + 0.1);
			}else {
				//记录实际上网流量
				temp = i;
				throw new Exception("本次上网花费"+i+"MB，您的余额不足，请充值后再使用");
			}
		}
		return temp;
	}



}
