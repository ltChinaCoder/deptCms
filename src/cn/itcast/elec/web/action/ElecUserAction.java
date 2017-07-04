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
			ils.saveLog(request, "����û�ģ�飺�����û�" + euf.getUserId());
		} else {
			eus.updateUserById(euf);
			ils.saveLog(request, "�޸��û�ģ�飺�޸��û�" + euf.getUserId());
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
		ils.saveLog(request, "ɾ���û�ģ�飺ɾ���û�" + euf.getUserName());
		return "save";
	}

	public String importPage() {
		return "importPage";
	}
    public String exportData()
    {
    	 //��ȡ��ʽ
    	 ArrayList filedName=eus.createFiledName();
    	//��ȡ����
    	 ArrayList filedData=eus.createFieldData(euf,request);
    	try {
    		//��ȡ�����
			OutputStream outputStream=response.getOutputStream();
			//��������ײ��Ŀհ��У�������jsp����html�ļ���ʱ�� html�ļ��ڲ�����ֺܶ�հ��У�
			//�����Ժ���ļ��ڵĺܶ�հ���Ҳ����ô������ ��ΪҪ����exccel������Ҫ�Ѱ׵�ȥ��
			response.reset();
			//���õ����ļ��ĸ�ʽΪexcel
			response.setContentType("application/vnd.ms-excel");
			ExcelFileGenerator excelFileGenerator=new ExcelFileGenerator(filedName, filedData);
			excelFileGenerator.expordExcel(outputStream);
			ils.saveLog(request, "����excelģ�飺����excel");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    	
    }
	public String importData() {
		eus.saveUserFromExcel(euf);
		ils.saveLog(request, "����excelģ�飺����excel");
		return "importData";
	}

	public void initSystemDdl() {
		List<ElecSystemDdlForm> sexList = isd.showKeyDdl("�Ա�");
		List<ElecSystemDdlForm> jctList = isd.showKeyDdl("ѧԺ");
		List<ElecSystemDdlForm> onDutyList = isd.showKeyDdl("�Ƿ���ְ");
		request.setAttribute("sexList", sexList);
		request.setAttribute("jctList", jctList);
		request.setAttribute("onDutyList", onDutyList);
	}
}
