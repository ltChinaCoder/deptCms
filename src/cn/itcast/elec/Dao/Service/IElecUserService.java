package cn.itcast.elec.Dao.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.web.form.ElecUserForm;

public interface IElecUserService {
	static String service_name = "cn.itcast.elec.Dao.Service.Impl.ElecUserServiceImpl";

	void saveUser(ElecUserForm euf);

	ElecUserForm findUserByid(ElecUserForm euf);

	void deleteUserById(HttpServletRequest request);

	String checkLogonName(String parameter);

	ElecUser checkName(String name);

	boolean checkPassword(ElecUser eu, String password);

	HashMap<String, String> findRole(String userID);

	List<ElecUserForm> findUser(ElecUserForm euf, HttpServletRequest request);

	ArrayList createFiledName();

	void saveUserFromExcel(ElecUserForm euf);

	List<ElecUserForm> finduserByChart();

	void updateUserById(ElecUserForm euf);

	ArrayList createFieldData(ElecUserForm euf2, HttpServletRequest request);
}
