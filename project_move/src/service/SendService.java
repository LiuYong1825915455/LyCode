package service;

import entity.MobileCard;

//发短信
public interface SendService {

	int send(int count,MobileCard card) throws Exception;
}
