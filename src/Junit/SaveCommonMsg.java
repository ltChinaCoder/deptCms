package Junit;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.Dao.IElecCommonMsgDao;
import cn.itcast.elec.domain.ElecCommonMsg;

public class SaveCommonMsg {
public static void main(String[] args) {
	 ApplicationContext  ac=new ClassPathXmlApplicationContext("beans.xml");
	IElecCommonMsgDao icm=(IElecCommonMsgDao) ac.getBean(IElecCommonMsgDao.service_name);
	ElecCommonMsg ecm=new ElecCommonMsg();
	ecm.setDevRun("≥‘ ∫2");
	ecm.setStationRun("dead");
	ecm.setCreateDate(new Date());
	icm.save(ecm);
}
}
