package Junit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.Dao.IElecUserDao;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.domain.ElecUserRole;
import cn.itcast.elec.util.MD5keyBean;
import cn.itcast.elec.util.StringHelper;

public class SaveUser {
public static void main(String[] args) {
	ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
	IElecUserDao ied=(IElecUserDao) ac.getBean(IElecUserDao.service_name);
	ElecUser eu=new ElecUser();
	
	eu.setBirthday(StringHelper.stringConvertToDate("1994-09-02"));
	eu.setContactTel("18813011951");
	eu.setEmail("513640302@qq.com");
	eu.setIsDuty("1");
	eu.setLoganName("513640302");
	MD5keyBean m5=new MD5keyBean();
	eu.setLoganPwd(m5.getkeyBeanofStr("123456"));
    eu.setMobile("1881301");
	eu.setJctID("2");
	eu.setSexID("2");
	eu.setUserName("¡ıÃŒ");
//	ElecUser eu1=new ElecUser();
//	eu1.setAddress("7∫≈¬•");
//	eu1.setBirthday(StringHelper.stringConvertToDate("1996-10-14"));
//	eu1.setIsDuty("2");
//	eu1.setLoganName("5136403022");
//	eu1.setLoganPwd("123");
//	eu1.setJctID("1");
//	eu1.setSexID("1");
//	eu1.setUserName("÷‹”Œ");
//	 eu1.setMobile("1581901");
	ied.save(eu);
//	ied.save(eu1);
//	ElecUserRole eur=new ElecUserRole();
}
}
