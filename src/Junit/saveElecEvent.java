package Junit;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.Dao.IElecEventDao;
import cn.itcast.elec.domain.ElecEvent;
import cn.itcast.elec.util.StringHelper;

public class saveElecEvent {
public static void main(String[] args) {
	 ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
	 IElecEventDao ied=(IElecEventDao) ac.getBean(IElecEventDao.service_name);
	 ElecEvent ee=new ElecEvent();
	 ee.setDoDate(StringHelper.stringConvertToDate("2016-12-12"));
	 ee.setRecordDate(new Date());
	 ee.setRemark("chishi");
	 ee.setScore(2);
	 ee.setUserId("201320722201");
	ied.save(ee);
}
}
