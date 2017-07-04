package cn.itcast.elec.Dao.Service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.Dao.IElecRolePopedomDao;
import cn.itcast.elec.Dao.Service.IElecRolePopedomService;
import cn.itcast.elec.domain.ElecRolePopedom;
import cn.itcast.elec.web.form.ElecUserRoleForm;
@Service(IElecRolePopedomService.service_name)
@Transactional(readOnly=true)
public class ElecRolePopedomServiceImpl implements IElecRolePopedomService{
@Resource(name=IElecRolePopedomDao.service_name)
private IElecRolePopedomDao ird;
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
@Override
public void setChomdRole(ElecUserRoleForm euf) {
	String role=euf.getRoleid();
	String[] selectOper=euf.getSelectoper();
	StringBuffer sb=new StringBuffer();
	for (int i = 0;selectOper!=null && i < selectOper.length; i++) {
		sb.append(selectOper[i]);
	}
	ElecRolePopedom erp=ird.findObjectById(role);
	if(erp==null)
	{
		ElecRolePopedom erp1=new ElecRolePopedom();
		erp1.setRoleID(role);
		erp1.setPopedomCode(sb.toString());
		ird.save(erp1);
	}
	else
	{
		erp.setPopedomCode(sb.toString());
		ird.updateObject(erp);
	}
}
}
