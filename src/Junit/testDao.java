package Junit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.Dao.IElecTextDao;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.web.form.ElecTextForm;

public class testDao {
	static ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
	static IElecTextDao<ElecText> itd=(IElecTextDao) ac.getBean(IElecTextDao.Service_Name);
	public static void main(String[] args) {
		// save
		ElecText et=new ElecText();
		et.setTextName("zhangyouyou");
		et.setTextDate(new Date());
		et.setTextRemark("chijiba");
      itd.save(et);
     // System.out.println(et.getTextID());
		//update
		/*ElecText et=new ElecText();
  		et.setTextID("8ae1b44a54fd169d0154fd16b4790001");
  		et.setTextDate(new Date());
  		et.setTextName("zhangsanfeng");
  		et.setTextRemark("old men");
  		itd.updateObject(et);*/
		//.findObjectById
		/*String textID="8ae1b44a54fd169d0154fd16b4790001";
		ElecText et=itd.findObjectById(textID);
		System.out.println(et.getTextName()+","+et.getTextRemark());*/
		//delete
		/*String textID="8ae1b44a54fd169d0154fd16b4790001";
		ElecText et=itd.findObjectById(textID);
		itd.deleteObjectById(et);*/
		//deleteObjcetsByIds
		/*String textids[]={"8ae1b44a54fa5fa70154fa5fb6450001","8ae1b44a54fd10c50154fd10d3070001"};
		itd.deleteObjectsByIds(textids);*/
		/*List<ElecText> list=new ArrayList<ElecText>();
		String id1="8ae1b44a54fd0d6c0154fd0d787b0001";
		String id2=" 8ae2bd3454f68a910154f68aa9ef0001";
		ElecText et=itd.findObjectById(id1);
		ElecText et2=itd.findObjectById(id2);
		list.add(et);
		list.add(et2);
		itd.deleteObjectsByIds(list);*/			
		}	
}
