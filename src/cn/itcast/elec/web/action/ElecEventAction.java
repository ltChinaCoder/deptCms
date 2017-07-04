package cn.itcast.elec.web.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import cn.itcast.elec.Container.ServiceProvider;
import cn.itcast.elec.Dao.Service.IElecEventService;
import cn.itcast.elec.Dao.Service.IElecLogService;
import cn.itcast.elec.Dao.Service.IElecSystemDdlService;
import cn.itcast.elec.util.ExcelFileGenerator;
import cn.itcast.elec.web.form.ElecEventForm;

public class ElecEventAction extends BaseAction implements ModelDriven<ElecEventForm> {
	private ElecEventForm eef = new ElecEventForm();
	private IElecEventService ees = (IElecEventService) ServiceProvider.getService(IElecEventService.service_name);
	private IElecSystemDdlService isd = (IElecSystemDdlService) ServiceProvider
			.getService(IElecSystemDdlService.service_name);
	private IElecLogService ils = (IElecLogService) ServiceProvider.getService(IElecLogService.service_name);

	@Override
	public ElecEventForm getModel() {
		return eef;
	}

	public String home() {
		List<ElecEventForm> lists = ees.findUser(eef, request);
		request.setAttribute("list", lists);
		long totalScore = 0;
		for (ElecEventForm elecEventForm : lists) {
			totalScore+=elecEventForm.getScore();
		}
		request.setAttribute("totalScore", totalScore);
		String flag = request.getParameter("initFlag");
		if (flag != null && flag.equals("1")) {
			return "list";
		}
		return "home";
	}
	public String save() {
		String eventId = eef.getEventId();
		if (eventId == null || "".equals(eventId)) {
			ees.saveEvent(eef);
			ils.saveLog(request, "添加事件模块：保存事件" + eef.getEventId());
		} else {
			ees.updateEventById(eef);
			ils.saveLog(request, "修改事件模块：修改事件" + eef.getEventId());
		}
		return "save";
	}

	public String add() {

		return "add";
	}

	public String edit() {
		ElecEventForm euf1 = ees.findEventByid(eef);
		request.setAttribute("user", euf1);
		return "edit";
	}
     public String export()
     {
    	 ArrayList fieldName=ees.createFiledName();
    	 ArrayList fieldData=ees.createFieldData(eef,request);
    	 try {
			OutputStream outputStream=response.getOutputStream();
			 response.reset();
			 response.setContentType("application/vnd.ms-excel");
	    	 ExcelFileGenerator excelFileGenerator=new ExcelFileGenerator(fieldName, fieldData);
	    	 excelFileGenerator.expordExcel(outputStream);
	 		ils.saveLog(request, "导出了excel");

		} catch (Exception e) {
			e.printStackTrace();
		}    	
    	 return null;
     }
	public String delete() {
		ees.deleteEvent(eef);
		ils.saveLog(request, "删除事件模块：删除事件" + eef.getUserId());
		System.out.println("event delete");
		return "delete";
	}

	public String importPage() {
		return "importPage";
	}

	public String importData() {
		ees.saveEventFromExcel(eef);
		ils.saveLog(request, "引入excel模块：引入excel");
		return "importData";
	}

	public String userHome() {
		List<ElecEventForm> list = ees.getUserEvent(eef, request);
		request.setAttribute("list", list);
		return "userHome";

	}
}
