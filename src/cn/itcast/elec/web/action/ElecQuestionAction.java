package cn.itcast.elec.web.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.elec.Container.ServiceProvider;
import cn.itcast.elec.Dao.Service.IElecLogService;
import cn.itcast.elec.Dao.Service.IElecQuestionService;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.web.form.ElecQuestionForm;

public class ElecQuestionAction extends BaseAction implements ModelDriven<ElecQuestionForm>{
private ElecQuestionForm eqf=new ElecQuestionForm();
private IElecLogService ils=(IElecLogService) ServiceProvider.getService(IElecLogService.service_name);
private IElecQuestionService iqs=(IElecQuestionService) ServiceProvider.getService(IElecQuestionService.service_name);
	@Override
	public ElecQuestionForm getModel() {
		return eqf;
}
public String home()
{
	return "home";
	}
public String save()
{
	iqs.saveQuestion(eqf,request);
	ils.saveLog(request,"提交问题模块:"+eqf.getUserId()+"提交了一个问题");
	return "save";
}
public String adminHome()
{
	List<ElecQuestionForm> list=iqs.getAllQuestion(eqf);
	request.setAttribute("list", list);
	return "adminHome";
	}
public String delete()
{
	iqs.deleteAllQuestion();
	ElecUser eu=(ElecUser) request.getSession().getAttribute("user");
	String userName=eu.getUserName();
	ils.saveLog(request,"删除问题模块:"+userName+"删除了全部问题");
	return "delete";}

}
