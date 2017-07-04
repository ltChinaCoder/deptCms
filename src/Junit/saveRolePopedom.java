package Junit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.Dao.IElecRolePopedomDao;
import cn.itcast.elec.domain.ElecRolePopedom;

public class saveRolePopedom {
 public static void main(String[] args) {
	 ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
	 IElecRolePopedomDao ird=(IElecRolePopedomDao) ac.getBean(IElecRolePopedomDao.service_name);
	 ElecRolePopedom er=new ElecRolePopedom();
	 er.setRoleID("1");
	 er.setPopedomCode("abcdefghijklmn");
	 ElecRolePopedom er1=new ElecRolePopedom();
	 er1.setRoleID("2");
	 er1.setPopedomCode("adefjkln");
	 ird.save(er);
	 ird.save(er1);
	 
}
}
