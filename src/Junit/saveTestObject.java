package Junit;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.Dao.IElecCommonMsgDao;
import cn.itcast.elec.domain.ElecCommonMsg;

public class saveTestObject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
         IElecCommonMsgDao icm=(IElecCommonMsgDao) ac.getBean(IElecCommonMsgDao.service_name);
         System.out.println(icm);
         ElecCommonMsg ecm=new ElecCommonMsg();
         ecm.setCreateDate(new Date());
         ecm.setDevRun("ok");
         ecm.setStationRun("ok");
         ElecCommonMsg ecm1=new ElecCommonMsg();
         ecm1.setCreateDate(new Date());
         ecm1.setDevRun("no");
         ecm1.setStationRun("no");
          icm.save(ecm);
          icm.save(ecm1);
         
	}

}
