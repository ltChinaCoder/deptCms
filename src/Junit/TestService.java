package Junit;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.Dao.Service.IElecTextService;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.web.form.ElecTextForm;

public class TestService {
public static void main(String[] args) {
	ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
	IElecTextService its=(IElecTextService) ac.getBean(IElecTextService.Service_Name);
	/*ElecText elecText=new ElecText();
	elecText.setTextDate(new Date());
	elecText.setTextName("≤‚ ‘service");
	elecText.setTextRemark("≤‚ ‘Service");
	its.saveElecText(elecText);*/
	ElecTextForm etf=new ElecTextForm();
	etf.setTextName("zhang");
	etf.setTextRemark("chi");
	List<ElecText> list =its.findByContionsNoPage(etf);
	for (ElecText elecText : list) {
		System.out.println(elecText.getTextName()+","+elecText.getTextRemark());
	}
}
}
