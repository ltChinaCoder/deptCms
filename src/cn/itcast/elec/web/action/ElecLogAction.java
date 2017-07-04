package cn.itcast.elec.web.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.elec.Container.ServiceProvider;
import cn.itcast.elec.Dao.Service.IElecLogService;
import cn.itcast.elec.web.form.ElecLogForm;

public class ElecLogAction extends BaseAction implements ModelDriven<ElecLogForm>{
private ElecLogForm ef=new ElecLogForm();
private IElecLogService els=(IElecLogService) ServiceProvider.getService(IElecLogService.service_name);
	@Override
	public ElecLogForm getModel() {
		return ef;
	}
public  String home()
{
	List<ElecLogForm> list=els.getAllLog(ef);
	request.setAttribute("logList", list);
	return "home";
	}
public String delete()
{
	els.deleteAllLog(ef);
	els.saveLog(request, "删除模块：删除所有日志");
	return "home";
	}
}
