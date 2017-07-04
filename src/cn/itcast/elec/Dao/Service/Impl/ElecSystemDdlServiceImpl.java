package cn.itcast.elec.Dao.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import antlr.StringUtils;
import cn.itcast.elec.Dao.IElecSystemDdlDao;
import cn.itcast.elec.Dao.Service.IElecSystemDdlService;
import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.domain.ElecSystemDdl;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecSystemDdlForm;
import cn.itcast.elec.web.form.ElecUserForm;
@Transactional(readOnly=true)
@Service(IElecSystemDdlService.service_name)
public class ElecSystemDdlServiceImpl implements IElecSystemDdlService {
 @Resource(name=IElecSystemDdlDao.service_name)
 private IElecSystemDdlDao idd;

@Override
public List<ElecSystemDdlForm> findKeyWords() {
	
	List<ElecSystemDdlForm> keyLists=idd.findKeyWords();
	return keyLists;
}

@Override
public List<ElecSystemDdlForm> showKeyDdl(String keyWord) {
	
	List<ElecSystemDdl> lists=this.getEditInf(keyWord);
	List<ElecSystemDdlForm> formLists=this.poObjectConvertToVo(lists);
	return formLists;
}

public List<ElecSystemDdl> getEditInf(String keyword) {
	String hqlwhere=" where keyword='"+keyword+"' "+"order by ";
	LinkedHashMap<String, String> orders=new LinkedHashMap<String, String>();
	orders.put("ddlCode", "asc");
	List<ElecSystemDdl> list=idd.findByContionsNoPage(hqlwhere, orders);
	return list;
}

public  List<ElecSystemDdlForm> poObjectConvertToVo(List<ElecSystemDdl> lists) {
	List<ElecSystemDdlForm> formlists=new ArrayList<ElecSystemDdlForm>();
	for (ElecSystemDdl elecSystemDdl : lists) {
		ElecSystemDdlForm edf=new ElecSystemDdlForm();
		edf.setDdlCode(Integer.toString(elecSystemDdl.getDdlCode()));
		edf.setDdlName(elecSystemDdl.getDdlName());
		formlists.add(edf);
	}
	return formlists;
}
@Transactional(isolation=Isolation.DEFAULT,readOnly=false,propagation=Propagation.REQUIRED)
@Override
public void addEditInfor(ElecSystemDdlForm esd) {
	String keyword=esd.getKeywordname();
	String flag=esd.getTypeflag();
	String []items=esd.getItemname();
	List<ElecSystemDdl> lists=new ArrayList<ElecSystemDdl>();
	if(flag!=null && "new".equals(flag))
	{
		this.saveSystemDdl(lists,items,keyword);		
	}
	else
	{
		List<ElecSystemDdl> list=this.getEditInf(keyword);
		idd.deleteObjectsByCollection(list);
		this.saveSystemDdl(lists, items, keyword);
	}
}

public  void saveSystemDdl(List<ElecSystemDdl> lists, String[] items, String keyword) {
	
	if(items!=null && items.length>0)
	{
		for (int i = 0; i < items.length; i++) {
			ElecSystemDdl esd1=new ElecSystemDdl();
			esd1.setDdlCode(i+1);
			esd1.setKeyword(keyword);
			esd1.setDdlName(items[i]);
			lists.add(esd1);
		}
	}
	idd.saveByCollection(lists);
}



}




