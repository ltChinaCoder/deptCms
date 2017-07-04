package cn.itcast.elec.Dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.Dao.IElecSystemDdlDao;
import cn.itcast.elec.domain.ElecSystemDdl;
import cn.itcast.elec.web.form.ElecSystemDdlForm;
@Repository(IElecSystemDdlDao.service_name)
public class ElecSystemDdlDaoImpl extends CommonDaoImpl<ElecSystemDdl> implements IElecSystemDdlDao{

	@Override
	public List<ElecSystemDdlForm> findKeyWords() {
		String hql="select distinct keyword from ElecSystemDdl ";
		List<String> keyLists=(List<String>) sessionFactory.getCurrentSession().find(hql);
		List<ElecSystemDdlForm> formlist=this.poObjectConvertVo(keyLists);
		return formlist;
	}

	private List<ElecSystemDdlForm> poObjectConvertVo(List<String> keyLists) {
		List<ElecSystemDdlForm> formlist=new ArrayList<ElecSystemDdlForm>();
		for (String string : keyLists) {
			ElecSystemDdlForm edf=new ElecSystemDdlForm();
			edf.setKeyword(string);
			formlist.add(edf);
		}
		return formlist;
	}	
}
