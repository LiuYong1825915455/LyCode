package service;

import entity.MobileCard;

//上网
public interface NetService {

	int flow(int flow,MobileCard card) throws Exception;
}
