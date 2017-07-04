package cn.itcast.elec.Dao.Service;

import java.util.List;

import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.util.XmlObject;
import cn.itcast.elec.web.form.ElecUserForm;
import cn.itcast.elec.web.form.ElecUserRoleForm;

public interface IElecUserRoleService {
static String service_name="IElecUserRoleService";

List findRoleList();

List<XmlObject> findXmlList();

List<XmlObject> findChomdList(ElecUserRoleForm euf);

List<ElecUserForm> findUserByRoleId(ElecUserRoleForm euf);

void setUserRole(ElecUserRoleForm euf);

void saveRole(ElecUserRoleForm euf);

String checkChmod(ElecUser eu);
}
