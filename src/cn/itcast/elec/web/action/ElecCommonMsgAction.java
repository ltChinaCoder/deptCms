package cn.itcast.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.elec.Container.ServiceProvider;
import cn.itcast.elec.Dao.Service.IElecCommonMsgService;
import cn.itcast.elec.Dao.Service.IElecLogService;
import cn.itcast.elec.web.form.ElecCommonMsgForm;

public class ElecCommonMsgAction extends BaseAction implements ModelDriven<ElecCommonMsgForm>{
  private ElecCommonMsgForm ecmf=new ElecCommonMsgForm();
  private IElecCommonMsgService iem=(IElecCommonMsgService) ServiceProvider.getService(IElecCommonMsgService.service_name);
  private IElecLogService ies=(IElecLogService) ServiceProvider.getService(IElecLogService.service_name);
	public ElecCommonMsgForm getModel() {
		return ecmf;
	}

public String home()
{
	List<ElecCommonMsgForm> list=iem.findElecCommonMsgList();
	request.setAttribute("commonList", list);
	return "home";
	}
public String save()
{
	iem.saveCommonMsg(ecmf);
	ies.saveLog(request,"添加了公告栏信息");
	return "save";
	}
public String edit()
{   
	 ElecCommonMsgForm ecm=iem.getEcmById(ecmf);
	request.setAttribute("ecmf", ecm);
	ies.saveLog(request, "修改了公告栏信息");
	return "edit";
	}
public String delete()
{
	  iem.deleteEcmById(ecmf);
	  ies.saveLog(request, "删除了公告栏信息");
	return "delete";
	}
}
