package Junit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.Dao.IElecUserRoleDao;
import cn.itcast.elec.domain.ElecUserRole;

public class saveUserRole {
 public static void main(String[] args) {
	 ApplicationContext  ac=new ClassPathXmlApplicationContext("beans.xml");
	  IElecUserRoleDao ird=(IElecUserRoleDao) ac.getBean(IElecUserRoleDao.service_name);
	  ElecUserRole eur=new ElecUserRole();
	  eur.setUserID("8ae2bd345596bfaa015596bfb2770001");
	  eur.setRoleID("1");
	  eur.setRemark("123");
	  ird.save(eur);
	//  ird.save(eur1);
}
}
