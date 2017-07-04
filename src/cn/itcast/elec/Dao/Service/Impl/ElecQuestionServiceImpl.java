package cn.itcast.elec.Dao.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.Dao.IElecQuestionDao;
import cn.itcast.elec.Dao.Service.IElecQuestionService;
import cn.itcast.elec.domain.ElecLog;
import cn.itcast.elec.domain.ElecQuestion;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.util.StringHelper;
import cn.itcast.elec.web.form.ElecLogForm;
import cn.itcast.elec.web.form.ElecQuestionForm;
@Service(IElecQuestionService.service_name)
@Transactional(readOnly=true)
public class ElecQuestionServiceImpl implements IElecQuestionService{
@Resource(name=IElecQuestionDao.service_name)
private IElecQuestionDao iqd;

@Override
@Transactional(readOnly=false,isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
public void saveQuestion(ElecQuestionForm eqf, HttpServletRequest request) {
	ElecQuestion eq=new ElecQuestion();
	eq.setRemark(eqf.getRemark());
	eq.setDoDate(new Date());
	ElecUser eu=(ElecUser) request.getSession().getAttribute("user");
	eq.setUserId(eu.getLoganName());
	iqd.save(eq);
}

@Override
public List<ElecQuestionForm> getAllQuestion(ElecQuestionForm eqf) {
	
		String s=null;
		if(eqf.getUserId()==null)
		{
			s="";
		}
		else
		{
			s="where userId like'%"+eqf.getUserId()+"%' ";
		}
		List<ElecQuestion> list=iqd.findByContionsNoPage(s, null);
		
		List<ElecQuestionForm> formList=this.poListToVo(list);
		return formList;
	}


private List<ElecQuestionForm> poListToVo(List<ElecQuestion> list) {
	List<ElecQuestionForm> list1=new ArrayList<ElecQuestionForm>();
	for (int i = 0; i < list.size(); i++) {
		ElecQuestionForm eqf=new ElecQuestionForm();
		eqf.setQuestionId(list.get(i).getQuestionId());
		eqf.setRemark(list.get(i).getRemark());
		eqf.setUserId(list.get(i).getUserId());
		eqf.setDoDate(StringHelper.dateConvertToString(list.get(i).getDoDate()));
		list1.add(eqf);
	}
	return list1;
}

@Override
@Transactional(readOnly=false,isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
public void deleteAllQuestion() {
	String hql="";
	List<ElecQuestion> list=iqd.findByContionsNoPage(hql, null);
	iqd.deleteObjectsByCollection(list);	
}







}
