package cn.itcast.elec.web.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.elec.Container.ServiceProvider;
import cn.itcast.elec.Dao.Service.IElecLogService;
import cn.itcast.elec.Dao.Service.IElecRolePopedomService;
import cn.itcast.elec.Dao.Service.IElecSystemDdlService;
import cn.itcast.elec.Dao.Service.IElecUserRoleService;
import cn.itcast.elec.util.XmlObject;
import cn.itcast.elec.web.form.ElecUserForm;
import cn.itcast.elec.web.form.ElecUserRoleForm;
public class ElecUserRoleAction extends BaseAction implements ModelDriven<ElecUserRoleForm>{
  private ElecUserRoleForm euf=new ElecUserRoleForm();
  private IElecLogService ils=(IElecLogService) ServiceProvider.getService(IElecLogService.service_name);
  private IElecUserRoleService eus=(IElecUserRoleService) ServiceProvider.getService(IElecUserRoleService.service_name);
  private IElecSystemDdlService isd=(IElecSystemDdlService) ServiceProvider.getService(IElecSystemDdlService.service_name);
  private IElecRolePopedomService ips=(IElecRolePopedomService) ServiceProvider.getService(IElecRolePopedomService.service_name);
	@Override
	public ElecUserRoleForm getModel() {
		return euf;
	}
	public String home()
	{
		List roleList=eus.findRoleList();
		request.setAttribute("roleList", roleList);
		List<XmlObject> xmllist=eus.findXmlList();		
		request.setAttribute("xmlList", xmllist);
		return "home";
	}
	public String edit()
	{
		
		List<XmlObject> xmllist=eus.findChomdList(euf);
		request.setAttribute("xmlList", xmllist);
		List<ElecUserForm> userlist=eus.findUserByRoleId(euf);
		request.setAttribute("userList", userlist);
		return "edit";
	}
	public String save()
	{
		
		eus.setUserRole(euf);
		eus.saveRole(euf);
		ils.saveLog(request, "修改了权限");
		return "save";
	}

}
