package cn.itcast.elec.Dao.Service.Impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.Dao.IElecTextDao;
import cn.itcast.elec.Dao.Impl.ElecTextDaoImpl;
import cn.itcast.elec.Dao.Service.IElecTextService;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.web.form.ElecTextForm;
@Transactional(readOnly=true)
@Service(IElecTextService.Service_Name)
public class ElecTextServiceImpl implements IElecTextService{
	@Resource(name=IElecTextDao.Service_Name)
	private IElecTextDao etd;
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveElecText(ElecText elecText) {		
		  etd.save(elecText);
	}
	@Override
	public List findByContionsNoPage(ElecTextForm etf) {
		 String hqlWhere="where 1=1 ";
		 if(etf!=null &&StringUtils.isNotBlank(etf.getTextName()))
		 {
			 hqlWhere+=" and textName like '%"+etf.getTextName()+"%' ";
		 }
		 if(etf!=null &&StringUtils.isNotBlank(etf.getTextRemark()))
		 {
			 hqlWhere+="and textRemark like '%"+etf.getTextRemark()+"%' order by ";
		 }
      LinkedHashMap<String, String> orders=new LinkedHashMap<String, String>();
       orders.put("textDate", "desc");
       orders.put("textName", "asc");
		List<ElecText> list= etd.findByContionsNoPage(hqlWhere,orders);
		return list;
	}

}
