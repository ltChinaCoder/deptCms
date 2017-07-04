package cn.itcast.elec.web.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.elec.Container.ServiceProvider;
import cn.itcast.elec.Dao.Service.IElecCommonMsgService;
import cn.itcast.elec.Dao.Service.IElecLogService;
import cn.itcast.elec.Dao.Service.IElecUserRoleService;
import cn.itcast.elec.Dao.Service.IElecUserService;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.util.CheckUtil;
import cn.itcast.elec.web.form.ElecCommonMsgForm;
import cn.itcast.elec.web.form.ElecMenuForm;

public class ElecMenuAction extends BaseAction implements ModelDriven<ElecMenuForm> {
	private ElecMenuForm emf = new ElecMenuForm();
	private Log log = LogFactory.getLog(ElecMenuAction.class);
	private IElecUserService ius = (IElecUserService) ServiceProvider.getService(IElecUserService.service_name);
	private IElecUserRoleService iur = (IElecUserRoleService) ServiceProvider
			.getService(IElecUserRoleService.service_name);
	private IElecCommonMsgService ims = (IElecCommonMsgService) ServiceProvider
			.getService(IElecCommonMsgService.service_name);
	private IElecLogService ils = (IElecLogService) ServiceProvider.getService(IElecLogService.service_name);

	@Override
	public ElecMenuForm getModel() {
		// TODO Auto-generated method stub
		return emf;
	}

	public String home() throws UnsupportedEncodingException {
		boolean flag1 = CheckUtil.checkNumber(request);
		if (!flag1) {
			this.addFieldError("error", "��֤��Ϊ�ջ��ߴ�������д����");
			return "error";
		}
		ElecUser eu = ius.checkName(emf.getName());
		String s = null;
		if (eu != null) {

			boolean flag = ius.checkPassword(eu, emf.getPassword());
			if (flag) {
				String chomds = iur.checkChmod(eu);
				if (StringUtils.isNotBlank(chomds)) {
					request.getSession().setAttribute("user", eu);
					request.getSession().setAttribute("chomd", chomds);
					HashMap<String, String> role = ius.findRole(eu.getUserID());
					request.getSession().setAttribute("role", role);
					// log.info("�û�"+eu.getUserName()+"��"+time+"��¼");
					CheckUtil.checkRemberMe(request, response);
					// ���ݿ�洢
					ils.saveLog(request, "��¼ģ��:" + emf.getName() + "��¼�ɹ�");
					s = "home";
				} else {
					this.addFieldError("error", "��û��Ȩ�޽��룡����ϵ����Ա");
					s = "error";
				}
			} else {
				this.addFieldError("error", "��ǰ��������벻��ȷ");
				s = "error";
			}
		} else {
			this.addFieldError("error", "��ǰ������˺Ų�����");
			s = "error";
		}
		return s;

	}

	public String left() {
		return "left";
	}

	public String change1() {
		return "change1";
	}

	public String loading() {
		return "loading";
	}

	public String title() {
		return "title";
	}

	public String alermJX() {
		return "alermJX";
	}

	public String alermSB() {
		ElecCommonMsgForm ema = ims.homeZdShow();
		request.setAttribute("SbMsg", ema);
		return "alermSB";
	}

	public String alermXZ() {
		return "alermXZ";
	}

	public String alermYS() {
		return "alermYS";
	}

	public String alermZD() {
		ElecCommonMsgForm ema = ims.homeZdShow();
		request.setAttribute("ZdMsg", ema);
		return "alermZD";
	}

	public String actingIndex() {
		return "actingIndex";
	}

	public String index() {
		request.getSession().invalidate();
		return "index";
	}

	public String notice() {

		return "notice";
	}
}
