package Junit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.Dao.IElecSystemDdlDao;
import cn.itcast.elec.domain.ElecSystemDdl;

public class testSystemDdl {
public static void main(String[] args) {
	 ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
	 IElecSystemDdlDao isd=(IElecSystemDdlDao) ac.getBean(IElecSystemDdlDao.service_name);
	 ElecSystemDdl es=new ElecSystemDdl();
	 es.setKeyword("角色类型");
	 es.setDdlCode(1);
	 es.setDdlName("系统管理员");
	 ElecSystemDdl es1=new ElecSystemDdl();
	 es1.setKeyword("角色类型");
	 es1.setDdlCode(2);
	 es1.setDdlName("高级管理员");
	 ElecSystemDdl es2=new ElecSystemDdl();
	 es2.setKeyword("角色类型");
	 es2.setDdlCode(3);
	 es2.setDdlName("中级管理员");
	 ElecSystemDdl es3=new ElecSystemDdl();
	 es3.setKeyword("角色类型");
	 es3.setDdlCode(4);
	 es3.setDdlName("业务用户");
	 ElecSystemDdl es4=new ElecSystemDdl();
	 es4.setKeyword("角色类型");
	 es4.setDdlCode(5);
	 es4.setDdlName("一般用户");
	 ElecSystemDdl es5=new ElecSystemDdl();
	 es5.setKeyword("角色类型");
	 es5.setDdlCode(6);
	 es5.setDdlName("普通用户");
	 isd.save(es);
	 isd.save(es1);
	 isd.save(es2);
	 isd.save(es3);
	 isd.save(es4);
	 isd.save(es5);
}
}
