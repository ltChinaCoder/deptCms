package cn.itcast.elec.Dao.Service.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.Dao.IElecRolePopedomDao;
import cn.itcast.elec.Dao.IElecSystemDdlDao;
import cn.itcast.elec.Dao.IElecUserDao;
import cn.itcast.elec.Dao.IElecUserRoleDao;
import cn.itcast.elec.Dao.Service.IElecSystemDdlService;
import cn.itcast.elec.Dao.Service.IElecUserRoleService;
import cn.itcast.elec.domain.ElecRolePopedom;
import cn.itcast.elec.domain.ElecSystemDdl;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.domain.ElecUserRole;
import cn.itcast.elec.util.XmlObject;
import cn.itcast.elec.web.form.ElecSystemDdlForm;
import cn.itcast.elec.web.form.ElecUserForm;
import cn.itcast.elec.web.form.ElecUserRoleForm;

@Transactional(readOnly = true)
@Service(IElecUserRoleService.service_name)
public class ElecUserRoleServiceImpl implements IElecUserRoleService {
	@Resource(name = IElecUserRoleDao.service_name)
	private IElecUserRoleDao iud;
	@Resource(name = IElecUserDao.service_name)
	private IElecUserDao iud1;
	@Resource(name = IElecSystemDdlService.service_name)
	private IElecSystemDdlService iss;
	@Resource(name = IElecSystemDdlDao.service_name)
	private IElecSystemDdlDao isd;
	@Resource(name = IElecRolePopedomDao.service_name)
	private IElecRolePopedomDao ird;

	@Override
	public List findRoleList() {
		List<ElecSystemDdlForm> lists = iss.showKeyDdl("角色类型");
		return lists;
	}

	@Override
	public List<XmlObject> findXmlList() {
		List<XmlObject> list = new ArrayList<XmlObject>();
		ServletContext context = ServletActionContext.getServletContext();

		String path = context.getRealPath("/WEB-INF/classes/Function.xml");
		File file = new File(path);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(file);
			Element element = document.getRootElement();
			for (Iterator<Element> iter = element.elementIterator("Function"); iter.hasNext();) {
				Element xmlElement = iter.next();
				XmlObject xmlObject = new XmlObject();
				xmlObject.setCode(xmlElement.elementText("FunctionCode"));
				xmlObject.setName(xmlElement.elementText("FunctionName"));
				xmlObject.setParentCode(xmlElement.elementText("ParentCode"));
				xmlObject.setParentName(xmlElement.elementText("ParentName"));
				list.add(xmlObject);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<XmlObject> findChomdList(ElecUserRoleForm euf) {
		//euf.getRole()
		ElecRolePopedom erp = ird.findObjectById(euf.getRole());
		List<XmlObject> chmodList = null;
		if (erp != null) {
			String chmods = erp.getPopedomCode();
			List<XmlObject> list = this.findXmlList();
			chmodList = new ArrayList<XmlObject>();
			for (XmlObject xmlObject : list) {
				if (chmods.contains(xmlObject.getCode())) {
					xmlObject.setFlag("1");

				} else
					xmlObject.setFlag("0");
				chmodList.add(xmlObject);
			}
			return chmodList;
		} else {
			ElecRolePopedom erp1 = new ElecRolePopedom();
			erp1.setRoleID(euf.getRole());
			erp1.setPopedomCode("");
			ird.save(erp1);
			return this.findXmlList();
		}

	}

	@Override
	public List<ElecUserForm> findUserByRoleId(ElecUserRoleForm euf) {

		List<Object[]> list = iud.findUserByRoleId(euf.getRole());
		List<ElecUserForm> formList = this.poChangToVo(list);
		return formList;
	}

	private List<ElecUserForm> poChangToVo(List<Object[]> list) {
		List<ElecUserForm> formList = new ArrayList<ElecUserForm>();
		String sqlwhere = null;
		String sqlwhere1 = null;
		List<ElecUserRole> list1 = null;
		List<ElecSystemDdl> list2 = null;
		for (Object[] objects : list) {
			ElecUserForm euf = new ElecUserForm();
			euf.setFlag(objects[0].toString());
			euf.setUserId(objects[1].toString());
			sqlwhere = "where userid='" + objects[1].toString() + "'";
			list1 = iud.findByContionsNoPage(sqlwhere, null);
			if (list1 != null && list1.size() > 0) {

				sqlwhere1 = "where ddlcode='" + list1.get(0).getRoleID() + "' and keyword='角色类型'";
				list2 = isd.findByContionsNoPage(sqlwhere1, null);
				//euf.setRoleName(list2.get(0).getDdlName());
				if(list2 != null && list2.size() > 0)
						euf.setRoleName(list2.get(0).getDdlName());
			}
			euf.setUserName(objects[2].toString());
			euf.setLogonName(objects[3].toString());
			formList.add(euf);
		}
		return formList;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void setUserRole(ElecUserRoleForm euf) {
		String roleId = euf.getRoleid();
		String[] userIds = euf.getSelectuser();
		for (int i = 0; i < userIds.length; i++) {
			String sqlwhere = " where userid='" + userIds[i] + "'";
			List<ElecUserRole> lists = iud.findByContionsNoPage(sqlwhere, null);
			iud.deleteObjectsByCollection(lists);
		}
		List<ElecUserRole> list = new ArrayList<ElecUserRole>();
		for (int i = 0; userIds != null && i < userIds.length; i++) {
			ElecUserRole eur = new ElecUserRole();
			eur.setUserID(userIds[i]);
			eur.setRoleID(roleId);
			list.add(eur);
		}
		iud.saveByCollection(list);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void saveRole(ElecUserRoleForm euf) {
		this.saveRolePopedom(euf);

	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)

	private void saveRolePopedom(ElecUserRoleForm euf) {
		String role = euf.getRoleid();
		String[] selectOper = euf.getSelectoper();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; selectOper != null && i < selectOper.length; i++) {
			sb.append(selectOper[i]);
		}
		ElecRolePopedom erp = ird.findObjectById(role);
		if (erp == null) {
			ElecRolePopedom erp1 = new ElecRolePopedom();
			erp1.setRoleID(role);
			erp1.setPopedomCode(sb.toString());
			ird.save(erp1);
		} else {
			erp.setPopedomCode(sb.toString());
			ird.updateObject(erp);
		}

	}

	@Override
	public String checkChmod(ElecUser eu) {
		String sqlWhere = "where loganname='" + eu.getLoganName() + "'";
		List<ElecUser> list = iud1.findByContionsNoPage(sqlWhere, null);
		String userid = list.get(0).getUserID();
		String sqlWhere2 = "where userid='" + userid + "'";
		List<ElecUserRole> list2 = iud.findByContionsNoPage(sqlWhere2, null);
		if (list2 != null && list2.size() > 0) {
			String roleid = list2.get(0).getRoleID();
			String sqlWhere3 = "where roleid='" + roleid + "'";
			List<ElecRolePopedom> list3 = ird.findByContionsNoPage(sqlWhere3, null);
			if (list3 != null && list3.size() > 0) {
				return list3.get(0).getPopedomCode();
			}
		}
		return null;
	}

}
