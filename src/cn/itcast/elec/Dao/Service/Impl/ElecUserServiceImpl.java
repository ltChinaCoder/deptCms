package cn.itcast.elec.Dao.Service.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.tribes.util.UUIDGenerator;
import org.apache.commons.lang.StringUtils;
import org.hibernate.id.UUIDHexGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.Dao.IElecSystemDdlDao;
import cn.itcast.elec.Dao.IElecUserDao;
import cn.itcast.elec.Dao.IElecUserRoleDao;
import cn.itcast.elec.Dao.Service.IElecUserService;
import cn.itcast.elec.domain.ElecSystemDdl;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.domain.ElecUserRole;
import cn.itcast.elec.util.GenerateSqlFromExcel;
import cn.itcast.elec.util.MD5keyBean;
import cn.itcast.elec.util.PageInfo;
import cn.itcast.elec.util.StringHelper;
import cn.itcast.elec.web.form.ElecUserForm;

@Transactional(readOnly = true)
@Service(IElecUserService.service_name)
public class ElecUserServiceImpl implements IElecUserService {
	@Resource(name = IElecUserDao.service_name)
	private IElecUserDao ied;
	@Resource(name = IElecUserRoleDao.service_name)
	private IElecUserRoleDao iud;
	@Resource(name = IElecSystemDdlDao.service_name)
	private IElecSystemDdlDao idd;
    private List resList=null;
	@Override
	public List<ElecUserForm> findUser(ElecUserForm euf, HttpServletRequest request) {
		String logonName = euf.getLogonName();
		String hqlWhere = null;
		if (logonName == null)
			hqlWhere = " order by ";
		else {
			hqlWhere = " where loganName like '%" + logonName + "%'  order by ";
		}
		LinkedHashMap<String, String> orders = new LinkedHashMap<String, String>();
		orders.put("userName", "asc");
		PageInfo pageInfo = new PageInfo(request);
		List<ElecUser> lists = ied.findByContionsWithPage(hqlWhere, orders, pageInfo);
		List<ElecUserForm> formList = this.poObjectConvertVo(lists);
		request.setAttribute("page", pageInfo.getPageBean());
		resList=formList;
		return formList;
	}

	private List<ElecUserForm> poObjectConvertVo(List<ElecUser> lists) {
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		for (ElecUser elecUser : lists) {
			ElecUserForm elecUserForm = new ElecUserForm();
			elecUserForm.setUserId(elecUser.getUserID());
			elecUserForm.setLogonName(elecUser.getLoganName());
			elecUserForm.setUserName(elecUser.getUserName());
			if(!StringUtils.isBlank(elecUser.getSexID()))
			elecUserForm.setSexId(this.getDdlInf(elecUser.getSexID(), "性别"));
			elecUserForm.setMobile(elecUser.getMobile());
			if(!StringUtils.isBlank(elecUser.getIsDuty()))
			elecUserForm.setIsDuty(this.getDdlInf(elecUser.getIsDuty(), "是否在职"));
			if(!StringUtils.isBlank(elecUser.getJctID()))
				elecUserForm.setJctId(this.getDdlInf(elecUser.getJctID(), "学院"));
			formList.add(elecUserForm);
		}
		return formList;
	}

	private String getDdlInf(String sexID, String string) {
		String hqlwhere=null;
		 hqlwhere = "where keyword='" + string + "' and ddlCode=" + Integer.valueOf(sexID);
		LinkedHashMap<String, String> orders = null;
		List<ElecSystemDdl> lists = idd.findByContionsNoPage(hqlwhere, orders);
		return (lists.get(0).getDdlName());
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW, readOnly = false)
	@Override
	public void saveUser(ElecUserForm euf) {
		ElecUser eu = this.voConvertToPo(euf);
		ied.save(eu);
		ElecUserRole eur = new ElecUserRole();
		eur.setRoleID("5");
		eur.setUserID(eu.getUserID());
		iud.save(eur);
	}

	private ElecUser voConvertToPoById(ElecUserForm euf) {
		ElecUser euf1 = ied.findObjectById(euf.getUserId());
		euf1.setUserID(euf.getUserId());
		euf1.setAddress(euf.getAddress());
		if (StringUtils.isNotBlank(euf.getBirthday()))
		euf1.setBirthday(StringHelper.stringConvertToDate(euf.getBirthday()));
		euf1.setContactTel(euf.getContactTel());
		if (StringUtils.isNotBlank(euf.getEmail()))
		  euf1.setEmail(euf.getEmail());
		euf1.setIsDuty(euf.getIsDuty());
		euf1.setJctID(euf.getJctId());
		euf1.setSexID(euf.getSexId());
		euf1.setUserName(euf.getUserName());
		if (StringUtils.isNotBlank(euf.getOnDutyDate()))
		euf1.setOnDutyDate(StringHelper.stringConvertToDate(euf.getOnDutyDate()));
		if (StringUtils.isNotBlank(euf.getOffDutyDate()))
		euf1.setOffDutyDate(StringHelper.stringConvertToDate(euf.getOffDutyDate()));
		euf1.setLoganName(euf.getLogonName());
		euf1.setRemark(euf.getRemark());
		if (StringUtils.isNotBlank(euf.getAddress()))

		euf1.setAddress(euf.getAddress());
		String md5Flag = euf.getMd5Flag();
		if (md5Flag != null && md5Flag.equals("1")) {
			euf1.setLoganPwd(euf.getLogonPwd());
		} else {
			MD5keyBean m1 = new MD5keyBean();
			euf1.setLoganPwd(m1.getkeyBeanofStr(euf.getLogonPwd()));
		}
		euf1.setMobile(euf.getMobile());
		if (StringUtils.isNotBlank(euf.getOnDutyDate()))

		euf1.setOnDutyDate(StringHelper.stringConvertToDate(euf.getOnDutyDate()));
		return euf1;

	}

	private ElecUser voConvertToPo(ElecUserForm euf) {
		ElecUser euf1 = new ElecUser();
		if (StringUtils.isNotBlank(euf.getAddress()))
			euf1.setAddress(euf.getAddress());
		if (StringUtils.isNotBlank(euf.getBirthday()))			
			euf1.setBirthday(StringHelper.stringConvertToDate(euf.getBirthday()));
		if (StringUtils.isNotBlank(euf.getContactTel()))
			euf1.setContactTel(euf.getContactTel());
		if (StringUtils.isNotBlank(euf.getEmail()))
			euf1.setEmail(euf.getEmail());
		euf1.setIsDuty(euf.getIsDuty());
		euf1.setJctID(euf.getJctId());
		euf1.setSexID(euf.getSexId());
		euf1.setUserName(euf.getUserName());
		euf1.setLoganName(euf.getLogonName());
		if (StringUtils.isNotBlank(euf.getRemark()))

			euf1.setRemark(euf.getRemark());
		MD5keyBean m1 = new MD5keyBean();
		if (StringUtils.isNotBlank(euf.getLogonPwd()))
			euf1.setLoganPwd(m1.getkeyBeanofStr(euf.getLogonPwd()));
		else
			euf1.setLoganPwd(m1.getkeyBeanofStr("000000"));
		euf1.setMobile(euf.getMobile());
		if (StringUtils.isNotBlank(euf.getOffDutyDate()))
			euf1.setOffDutyDate(StringHelper.stringConvertToDate(euf.getOffDutyDate()));
		if (StringUtils.isNotBlank(euf.getOnDutyDate()))

			euf1.setOnDutyDate(StringHelper.stringConvertToDate(euf.getOnDutyDate()));
		return euf1;
	}

	@Override
	public ElecUserForm findUserByid(ElecUserForm euf) {
		ElecUser eu = ied.findObjectById(euf.getUserId());
		euf = this.poConvertToVo(eu);
		return euf;
	}

	private ElecUserForm poConvertToVo(ElecUser eu) {
		ElecUserForm euf = new ElecUserForm();
		euf.setAddress(eu.getAddress());

		euf.setBirthday(StringHelper.dateConvertToString(eu.getBirthday()));
		euf.setContactTel(eu.getContactTel());
		euf.setEmail(eu.getEmail());
		euf.setIsDuty(eu.getIsDuty());
		euf.setJctId(eu.getJctID());
		euf.setLogonName(eu.getLoganName());
		MD5keyBean m1 = new MD5keyBean();
		if (StringUtils.isNotBlank(eu.getLoganPwd()))

			euf.setLogonPwd((eu.getLoganPwd()));
		else
			euf.setLogonPwd(m1.getkeyBeanofStr("000000"));
		euf.setMobile(eu.getMobile());
		euf.setOnDutyDate(StringHelper.dateConvertToString(eu.getOnDutyDate()));
		euf.setOffDutyDate(StringHelper.dateConvertToString(eu.getOffDutyDate()));
		euf.setSexId(eu.getSexID());
		euf.setUserName(eu.getUserName());
		euf.setRemark(eu.getRemark());
		return euf;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void deleteUserById(HttpServletRequest request) {
		ied.deleteObjectById(ied.findObjectById(request.getParameter("userId")));
	}

	@Override
	public String checkLogonName(String parameter) {
		String hqlWhere = " where loganname= '" + parameter + "' ";
		List<ElecUser> list = ied.findByContionsNoPage(hqlWhere, null);
		if (list != null && list.size() > 0) {
			return "1";
		} else {
			return "2";
		}
	}

	@Override
	public ElecUser checkName(String name) {
		String sqlWhere = "where loganname='" + name + "'";
		List<ElecUser> list = ied.findByContionsNoPage(sqlWhere, null);
		ElecUser eu = null;
		if (list != null && list.size() > 0) {
			eu = list.get(0);
		}
		return eu;
	}

	@Override
	public boolean checkPassword(ElecUser eu, String password) {
		MD5keyBean m1 = new MD5keyBean();
		if (eu.getLoganPwd().equals(m1.getkeyBeanofStr(password))) {
			return true;
		}
		return false;
	}

	@Override
	public HashMap<String, String> findRole(String userID) {
		HashMap<String, String> hm = new HashMap<String, String>();
		String sqlWhere = "where userid='" + userID + "'";
		List<ElecUserRole> list = iud.findByContionsNoPage(sqlWhere, null);
		String roleid = list.get(0).getRoleID();
		String sqlWhere1 = "where ddlCode='" + roleid + "'";
		List<ElecSystemDdl> list1 = idd.findByContionsNoPage(sqlWhere1, null);
		String roleName = list1.get(0).getDdlName();
		hm.put(roleid, roleName);
		return hm;
	}

	@Override
	public ArrayList createFiledName() {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		String[] s = { "登录id", "用户名", "性别", "手机", "是否在职","学院" };
		for (int i = 0; i < s.length; i++) {
			list.add(s[i]);
		}
		return list;
	}

	@Override
	public ArrayList createFieldData(ElecUserForm euf2, HttpServletRequest request) {
		ArrayList list = new ArrayList();
		for (int i = 0; i < resList.size(); i++) {
			ElecUserForm object = (ElecUserForm) resList.get(i);
			ArrayList data = new ArrayList();
			data.add(object.getLogonName());
			data.add(object.getUserName());
			data.add(object.getSexId());
			data.add(object.getMobile());
			data.add(object.getIsDuty());
			data.add(object.getJctId());
			list.add(data);
		}
		return list;
	}

	@Override
	public List<ElecUserForm> finduserByChart() {
		List<Object[]> list = ied.findUserByChart();
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		for (int i = 0; i < list.size(); i++) {
			Object[] o = list.get(i);
			ElecUserForm euf = new ElecUserForm();
			euf.setJctName(o[0].toString());
			euf.setJctCount(o[1].toString());
			formList.add(euf);
		}
		return formList;
	}
	
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void saveUserFromExcel(ElecUserForm euf) {
		File file = euf.getFile();
		ElecUser elecUser=null;
		GenerateSqlFromExcel excel=new GenerateSqlFromExcel();
		try {
			ArrayList<String[]> generateStationBugSql = GenerateSqlFromExcel.generateStationBugSql(file);
		   if(generateStationBugSql!=null)
		   {
			   for (int i = 0; i < generateStationBugSql.size(); i++) {
				  String []strings=generateStationBugSql.get(i);
				  elecUser=new ElecUser();
				   elecUser.setLoganName(strings[0]);
				   elecUser.setLoganPwd(new MD5keyBean().getkeyBeanofStr(strings[1]));
				   elecUser.setUserName(strings[2]);
				   elecUser.setSexID(strings[3]);
				   elecUser.setJctID(strings[4]);
				   elecUser.setIsDuty(strings[5]);
				   elecUser.setOnDutyDate(StringHelper.stringConvertToDate(strings[6]));
				   ied.save(elecUser);
				    ElecUserRole eur = new ElecUserRole();
					eur.setRoleID("5");
					eur.setUserID(elecUser.getUserID());
					iud.save(eur);
			}
		   }
			  
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW, readOnly = false)
	@Override
	public void updateUserById(ElecUserForm euf) {
		ElecUser eu = this.voConvertToPoById(euf);
		ied.updateObject(eu);
	}
}
