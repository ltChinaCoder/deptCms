package Junit;

import java.util.Date;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cn.itcast.elec.domain.ElecText;

public class testHibernate {
public static void main(String[] args) {
	Configuration cf=new Configuration();
	cf.configure();
    SessionFactory sf=cf.buildSessionFactory();
    Session session=sf.openSession();
    System.out.println(session);
//    session.getTransaction().begin();
//    ElecText et=new ElecText();
//    et.setTextDate(new Date());
//    et.setTextName("liutao");
//    et.setTextRemark("you are handsome");
//    session.saveOrUpdate(et);
//   session.getTransaction().commit();
//   session.close();
}
}
