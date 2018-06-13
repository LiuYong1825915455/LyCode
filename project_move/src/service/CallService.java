package service;

import entity.MobileCard;

//打电话
public interface CallService {

	int call(int minCount,MobileCard card) throws Exception;
}
