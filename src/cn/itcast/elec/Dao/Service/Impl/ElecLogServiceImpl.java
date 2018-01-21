package cn.itcast.elec.Dao.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.Dao.IElecLogDao;
import cn.itcast.elec.Dao.Service.IElecLogService;
import cn.itcast.elec.domain.ElecLog;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.web.form.ElecLogForm;

@Service(IElecLogService.service_name)
@Transactional(readOnly = true)
public class ElecLogServiceImpl implements IElecLogService {
	@Resource(name = IElecLogDao.service_name)
	private IElecLogDao ild;

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void saveLog(HttpServletRequest request, String string) {
		ElecLog el = new ElecLog();
		el.setDetails(string);
		el.setIpAddress(request.getRemoteAddr());
		el.setOpeTime(new Date());
		ElecUser eu = (ElecUser) request.getSession().getAttribute("user");
		el.setOpeName(eu.getUserName());
		ild.save(el);
	}

	@Override
	public List<ElecLogForm> getAllLog(ElecLogForm ef) {
		String s = null;
		if (ef.getOpeName() == null) {
			s = "";
		} else {
			s = "where opeName like'%" + ef.getOpeName() + "%' " + "order by opetime desc";
		}
		List<ElecLog> list = ild.findByContionsNoPage(s, null);

		List<ElecLogForm> formList = this.voToPoList(list);
		return formList;
	}

	private List<ElecLogForm> voToPoList(List<ElecLog> list) {
		List<ElecLogForm> formList = new ArrayList<ElecLogForm>();
		for (ElecLog elecLog : list) {
			ElecLogForm elg = new ElecLogForm();
			elg.setDetails(elecLog.getDetails());
			elg.setIpAddress(elecLog.getIpAddress());
			elg.setOpeName(elecLog.getOpeName());
			elg.setOpeTime(elecLog.getOpeTime());
			formList.add(elg);
		}
		return formList;
	}

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public void deleteAllLog(ElecLogForm ef) {
		List<ElecLog> list = ild.findByContionsNoPage("", null);
		ild.deleteObjectsByCollection(list);
	}

}
