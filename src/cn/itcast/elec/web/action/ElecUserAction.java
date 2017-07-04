package cn.itcast.elec.web.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ModelDriven;
import cn.itcast.elec.Container.ServiceProvider;
import cn.itcast.elec.Dao.Service.IElecLogService;
import cn.itcast.elec.Dao.Service.IElecSystemDdlService;
import cn.itcast.elec.Dao.Service.IElecUserService;
import cn.itcast.elec.util.ExcelFileGenerator;
import cn.itcast.elec.web.form.ElecSystemDdlForm;
import cn.itcast.elec.web.form.ElecUserForm;

public class ElecUserAction extends BaseAction implements ModelDriven<ElecUserForm> {
	private ElecUserForm euf = new ElecUserForm();
	private IElecUserService eus = (IElecUserService) ServiceProvider.getService(IElecUserService.service_name);
	private IElecSystemDdlService isd = (IElecSystemDdlService) ServiceProvider
			.getService(IElecSystemDdlService.service_name);
	private IElecLogService ils = (IElecLogService) ServiceProvider.getService(IElecLogService.service_name);

	@Override
	public ElecUserForm getModel() {
		return euf;
	}

	public String home() {
		List<ElecUserForm> lists = eus.findUser(euf, request);
		request.setAttribute("list", lists);
		String flag = request.getParameter("initFlag");
		if (flag != null && flag.equals("1")) {
			return "list";
		}
		return "home";
	}
	public String add() {
		this.initSystemDdl();
		return "add";
	}

	public String save() {
		String useId = euf.getUserId();
		if (useId == null || "".equals(useId)) {
			eus.saveUser(euf);
			ils.saveLog(request, "添加用户模块：保存用户" + euf.getUserId());
		} else {
			eus.updateUserById(euf);
			ils.saveLog(request, "修改用户模块：修改用户" + euf.getUserId());
		}
		String s = null;
		if (euf.getRoleFlag() != null && euf.getRoleFlag().equals("1"))
			return edit();
		else
			s = "save";
		return s;
	}
	public String edit() {
		ElecUserForm euf1 = eus.findUserByid(euf);
		request.setAttribute("user", euf1);
		request.setAttribute("viewFlag", euf.getViewFlag());
		this.initSystemDdl();
		String roleFlag = euf1.getRoleFlag();
		request.setAttribute("roleFlag", roleFlag);
		return "edit";
	}

	public String delete() {
		eus.deleteUserById(request);
		ils.saveLog(request, "删除用户模块：删除用户" + euf.getUserName());
		return "save";
	}

	public String importPage() {
		return "importPage";
	}
    public String exportData()
    {
    	 //获取格式
    	 ArrayList filedName=eus.createFiledName();
    	//获取数据
    	 ArrayList filedData=eus.createFieldData(euf,request);
    	try {
    		//获取输出流
			OutputStream outputStream=response.getOutputStream();
			//用来清除首部的空白行（就是在jsp生成html文件的时候 html文件内部会出现很多空白行）
			//下载以后的文件内的很多空白行也是这么产生的 因为要导出exccel所以需要把白的去掉
			response.reset();
			//设置导出文件的格式为excel
			response.setContentType("application/vnd.ms-excel");
			ExcelFileGenerator excelFileGenerator=new ExcelFileGenerator(filedName, filedData);
			excelFileGenerator.expordExcel(outputStream);
			ils.saveLog(request, "导出excel模块：导出excel");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    	
    }
	public String importData() {
		eus.saveUserFromExcel(euf);
		ils.saveLog(request, "引入excel模块：引入excel");
		return "importData";
	}

	public void initSystemDdl() {
		List<ElecSystemDdlForm> sexList = isd.showKeyDdl("性别");
		List<ElecSystemDdlForm> jctList = isd.showKeyDdl("学院");
		List<ElecSystemDdlForm> onDutyList = isd.showKeyDdl("是否在职");
		request.setAttribute("sexList", sexList);
		request.setAttribute("jctList", jctList);
		request.setAttribute("onDutyList", onDutyList);
	}
}
