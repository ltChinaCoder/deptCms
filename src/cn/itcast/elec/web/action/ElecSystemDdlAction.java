package cn.itcast.elec.web.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.elec.Container.ServiceProvider;
import cn.itcast.elec.Dao.Service.IElecLogService;
import cn.itcast.elec.Dao.Service.IElecSystemDdlService;
import cn.itcast.elec.web.form.ElecSystemDdlForm;

public class ElecSystemDdlAction extends BaseAction implements ModelDriven<ElecSystemDdlForm>{
 private ElecSystemDdlForm esd=new ElecSystemDdlForm();
 private IElecSystemDdlService isd=(IElecSystemDdlService) ServiceProvider.getService(IElecSystemDdlService.service_name);
 private IElecLogService ils=(IElecLogService) ServiceProvider.getService(IElecLogService.service_name);
 @Override
	public ElecSystemDdlForm getModel() {
		return esd;
	}
	public String home()
	{
		List<ElecSystemDdlForm> lists=isd.findKeyWords();		
		request.setAttribute("keyList", lists);
		return "home";
	}
	public String edit()
	{
		String keyWord=esd.getKeyword();
		List<ElecSystemDdlForm> lists=isd.showKeyDdl(keyWord);				
		request.setAttribute("list", lists);
		return "edit";
	}
	public String save()
	{
		if(esd.getKeyword()==null || esd.getKeyword().equals(""))
		{
			ils.saveLog(request, "添加字典模块：添加字典"+esd.getKeyword());
		}
		else
		{
			ils.saveLog(request, "修改字典模块：修改字典"+esd.getKeyword());
		}		
		isd.addEditInfor(esd);
		return "save";
	}

}
