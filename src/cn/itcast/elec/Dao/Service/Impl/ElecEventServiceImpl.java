package cn.itcast.elec.Dao.Service.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.Dao.IElecEventDao;
import cn.itcast.elec.Dao.Service.IElecEventService;
import cn.itcast.elec.domain.ElecEvent;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.domain.ElecUserRole;
import cn.itcast.elec.util.GenerateSqlFromExcel;
import cn.itcast.elec.util.MD5keyBean;
import cn.itcast.elec.util.PageInfo;
import cn.itcast.elec.util.StringHelper;
import cn.itcast.elec.web.form.ElecEventForm;

@Service(IElecEventService.service_name)
@Transactional(readOnly = true)
public class ElecEventServiceImpl implements IElecEventService {
	@Resource(name = IElecEventDao.service_name)
	private IElecEventDao ied;
	private List resList = null;

	@Override
	public List<ElecEventForm> findUser(ElecEventForm eef, HttpServletRequest request) {
		StringBuffer hqlWhere = new StringBuffer();
		hqlWhere.append(" where 1=1 ");
		if (StringUtils.isNotBlank(eef.getUserId()))
			hqlWhere.append("and userId like'%" + eef.getUserId() + "%'");
		if (StringUtils.isNotBlank(eef.getMinScore()))
			hqlWhere.append(" and score >=" + eef.getMinScore());
		if (StringUtils.isNotBlank(eef.getMaxScore()))
			hqlWhere.append(" and score <= " + eef.getMaxScore());
		if (StringUtils.isNotBlank(eef.getStartTime()))
			hqlWhere.append(" and doDate >=" + this.getDate(eef.getStartTime()));
		if (StringUtils.isNotBlank(eef.getEndTime()))
			hqlWhere.append(" and doDate <=" + this.getDate(eef.getEndTime()));
		hqlWhere.append(" order by ");
		LinkedHashMap<String, String> orders = new LinkedHashMap<String, String>();
		orders.put("userId", "asc");
		PageInfo pageInfo = new PageInfo(request);
		List<ElecEvent> lists = ied.findByContionsWithPage(hqlWhere.toString(), orders, pageInfo);
		List<ElecEventForm> formList = this.poObjectConvertVo(lists);
		request.setAttribute("page", pageInfo.getPageBean());
		resList = formList;
		return formList;
	}

	private List<ElecEventForm> poObjectConvertVo(List<ElecEvent> lists) {
		List<ElecEventForm> formList = new ArrayList<ElecEventForm>();
		for (int i = 0; i < lists.size(); i++) {
			ElecEvent ee = lists.get(i);
			ElecEventForm eef = new ElecEventForm();
			eef.setDoDate(StringHelper.dateConvertToString(ee.getDoDate()));
			eef.setEventId(ee.getEventId());
			eef.setRecordDate(StringHelper.dateConvertToString(ee.getRecordDate()));
			eef.setRemark(ee.getRemark());
			eef.setUserId(ee.getUserId());
			eef.setScore(ee.getScore());
			formList.add(eef);
		}
		return formList;
	}

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public void saveEvent(ElecEventForm eef) {
		ElecEvent ee = new ElecEvent();
		ee.setDoDate(StringHelper.stringConvertToDate(eef.getDoDate()));
		ee.setRecordDate(new Date());
		ee.setRemark(eef.getRemark());
		ee.setScore(eef.getScore());
		ee.setUserId(eef.getUserId());
		ied.save(ee);
	}

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public void deleteEvent(ElecEventForm eef) {
		String eventId = eef.getEventId();
		ElecEvent ee = ied.findObjectById(eventId);
		ied.deleteObjectById(ee);
	}

	@Override
	public ElecEventForm findEventByid(ElecEventForm eef) {
		String id = eef.getEventId();
		if (id == null)
			return null;
		ElecEvent ecm = ied.findObjectById(id);
		return this.poObjectConvertToVo(ecm);
	}

	private ElecEventForm poObjectConvertToVo(ElecEvent ecm) {
		ElecEventForm eef = new ElecEventForm();
		eef.setDoDate(StringHelper.dateConvertToString(ecm.getDoDate()));
		eef.setRemark(ecm.getRemark());
		eef.setScore(ecm.getScore());
		eef.setEventId(ecm.getEventId());
		eef.setUserId(ecm.getUserId());
		return eef;
	}

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public void updateEventById(ElecEventForm eef) {
		ElecEvent ee = ied.findObjectById(eef.getEventId());
		System.out.println(ee);
		ee.setDoDate(StringHelper.stringConvertToDate(eef.getDoDate()));
		ee.setRemark(eef.getRemark());
		ee.setUserId(eef.getUserId());
		System.out.println(ee);
		ied.updateObject(ee);
	}

	@Override
	public ArrayList createFiledName() {
		ArrayList list = new ArrayList();
		String[] s = { "用户id", "学时", "内容", "志愿时间", "记录时间" };
		for (int i = 0; i < s.length; i++) {
			list.add(s[i]);
		}
		return list;
	}

	@Override
	public ArrayList createFieldData(ElecEventForm eef2, HttpServletRequest request) {
		ArrayList list = new ArrayList();
		for (int i = 0; i < resList.size(); i++) {
			ElecEventForm eef = (ElecEventForm) resList.get(i);
			ArrayList list1 = new ArrayList();
			list1.add(eef.getUserId());
			list1.add(eef.getScore());
			list1.add(eef.getRemark());
			list1.add(eef.getDoDate());
			list1.add(eef.getRecordDate());
			list.add(list1);
		}
		System.out.println(list);
		return list;

	}

	public String getDate(String s) {
		String[] s1 = s.split("-");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s1.length; i++) {
			sb.append(s1[i]);
		}
		return sb.toString();
	}

	@Override
	public List<ElecEventForm> getUserEvent(ElecEventForm eef, HttpServletRequest request) {
		String userId = request.getParameter("userId");
		StringBuffer hqlWhere = new StringBuffer();
		hqlWhere.append("where 1=1 ");
		hqlWhere.append("and userId ='" + userId + "'");
		if (StringUtils.isNotBlank(eef.getMinScore()))
			hqlWhere.append(" and score >=" + eef.getMinScore());
		if (StringUtils.isNotBlank(eef.getMaxScore()))
			hqlWhere.append(" and score <= " + eef.getMaxScore());
		if (StringUtils.isNotBlank(eef.getStartTime()))
			hqlWhere.append(" and doDate >=" + this.getDate(eef.getStartTime()));
		if (StringUtils.isNotBlank(eef.getEndTime()))
			hqlWhere.append(" and doDate <=" + this.getDate(eef.getEndTime()));
		List<ElecEvent> list = ied.findByContionsNoPage(hqlWhere.toString(), null);
		List<ElecEventForm> formList = this.poObjectConvertVo(list);
		return formList;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)

	public void saveEventFromExcel(ElecEventForm eef) {
		File file = eef.getFile();
		ElecEvent elecEvent = null;
		GenerateSqlFromExcel excel = new GenerateSqlFromExcel();
		try {
			ArrayList<String[]> generateStationBugSql = GenerateSqlFromExcel.generateStationBugSql(file);
			if (generateStationBugSql != null) {
				for (int i = 0; i < generateStationBugSql.size(); i++) {
					String[] strings = generateStationBugSql.get(i);
					elecEvent = new ElecEvent();
					elecEvent.setUserId(strings[0]);
					elecEvent.setRecordDate(new Date());
					elecEvent.setScore(Integer.valueOf(strings[1]));
					elecEvent.setRemark(strings[2]);
					elecEvent.setDoDate(StringHelper.stringConvertToDate(strings[3]));
					ied.save(elecEvent);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
