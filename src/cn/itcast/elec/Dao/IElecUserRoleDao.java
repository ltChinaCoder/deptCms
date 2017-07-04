package cn.itcast.elec.Dao;

import java.util.List;

import cn.itcast.elec.domain.ElecUserRole;

public interface IElecUserRoleDao extends ICommonDao<ElecUserRole>{
static String service_name="cn.itcast.elec.Dao.Impl.ElecUserRoleDaoImpl";

List<Object[]> findUserByRoleId(String role);


}
