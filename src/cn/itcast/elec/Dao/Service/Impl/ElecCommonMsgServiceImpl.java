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

import com.sun.org.apache.xml.internal.security.utils.ElementChecker;

import cn.itcast.elec.Dao.IElecCommonMsgDao;
import cn.itcast.elec.Dao.Service.IElecCommonMsgService;
import cn.itcast.elec.Dao.Service.IElecTextService;
import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.util.PageInfo;
import cn.itcast.elec.util.StringHelper;
import cn.itcast.elec.web.action.ElecCommonMsgAction;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
@Transactional(readOnly=true)
@Service(IElecCommonMsgService.service_name)
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService{
	@Resource(name=IElecCommonMsgDao.service_name)
	private IElecCommonMsgDao<ElecCommonMsg> icm;

	@Override
	public List<ElecCommonMsgForm> findElecCommonMsgList() {
		String hqlWhere="order by ";
		LinkedHashMap<String, String> lmh=new LinkedHashMap<String, String>();
		lmh.put("createDate", "desc");
		List<ElecCommonMsg> list=icm.findByContionsNoPage(hqlWhere, lmh);
		List<ElecCommonMsgForm> formList=this.voConvertPo(list);
		System.out.println();
		return formList;
	}

	private List<ElecCommonMsgForm> voConvertPo(List<ElecCommonMsg> list) {
		
		List<ElecCommonMsgForm> formList=new ArrayList<ElecCommonMsgForm>();
		for (ElecCommonMsg elecCommonMsg : list) {
			ElecCommonMsgForm emf=new ElecCommonMsgForm();
			emf.setComID(elecCommonMsg.getComID());
			emf.setDevRun(elecCommonMsg.getDevRun());
			emf.setStationRun(elecCommonMsg.getStationRun());
			emf.setCreateDate(String.valueOf(elecCommonMsg.getCreateDate()));
			formList.add(emf);
		}
		return formList;
	}
	 @Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public void saveCommonMsg(ElecCommonMsgForm ecmf) {
		 
	String id=ecmf.getComID();
	
	if(id==null ||"".equals(id))
	{
		ElecCommonMsg ecm=this.voObjectConvertToPo(ecmf);
		icm.save(ecm);	
	}
	else
	{
		ElecCommonMsg ecm=icm.findObjectById(id);
		ecm.setDevRun(ecmf.getDevRun());
		ecm.setStationRun(ecmf.getStationRun());
		icm.updateObject(ecm);
	}
	}
   
	private ElecCommonMsg voObjectConvertToPo(ElecCommonMsgForm emg) {
		 ElecCommonMsg ecm=new ElecCommonMsg();
		 ecm.setCreateDate(new Date());
		 ecm.setDevRun(emg.getDevRun());
		 ecm.setStationRun(emg.getStationRun());
		 return ecm;
	}

	@Override
	public ElecCommonMsgForm homeZdShow() {
		String hqlWhere="order by ";
		LinkedHashMap<String, String> lmh=new LinkedHashMap<String, String>();
		lmh.put("createDate", "asc");
		List<ElecCommonMsg> list=icm.findByContionsNoPage(hqlWhere, lmh);
		List<ElecCommonMsgForm> formList=this.voConvertPo(list);
		ElecCommonMsgForm emf=new ElecCommonMsgForm();
		if(formList.size()>1)
		emf=formList.get(formList.size()-1);
		else
			emf=formList.get(0);
		return emf;
	}

	@Override
	public ElecCommonMsgForm getEcmById(ElecCommonMsgForm ecmf) {
		String id=ecmf.getComID();
		if(id==null)
			return null;
		ElecCommonMsg ecm=icm.findObjectById(id);
		return this.poObjectConvertToVo(ecm);
			
	}

	private ElecCommonMsgForm poObjectConvertToVo(ElecCommonMsg ecm) {
		 ElecCommonMsgForm ecmf=new ElecCommonMsgForm();
		 ecmf.setComID(ecm.getComID());
		 ecmf.setDevRun(ecm.getDevRun());
		 ecmf.setStationRun(ecm.getStationRun());
		 ecmf.setCreateDate(StringHelper.dateConvertToString(ecm.getCreateDate()));
		 return ecmf;
	}
	 @Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public void deleteEcmById(ElecCommonMsgForm ecmf) {
		ElecCommonMsg ecm=icm.findObjectById(ecmf.getComID());
		icm.deleteObjectById(ecm);
	}
	
}
