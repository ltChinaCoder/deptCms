package cn.itcast.elec.Dao;

import java.util.LinkedHashMap;
import java.util.List;

import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.util.PageInfo;

public interface IElecUserDao extends ICommonDao<ElecUser>{
static String service_name="cn.itcast.elec.Dao.Impl.ElecUserDaoImpl";

List<Object[]> findUserByChart();




}
