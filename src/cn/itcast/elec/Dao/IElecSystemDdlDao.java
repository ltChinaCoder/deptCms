package cn.itcast.elec.Dao;

import java.util.List;

import cn.itcast.elec.domain.ElecSystemDdl;
import cn.itcast.elec.web.form.ElecSystemDdlForm;

public interface IElecSystemDdlDao extends ICommonDao<ElecSystemDdl>{
 static String  service_name="cn.itcast.elec.Dao.Impl.ElecSystemDdlDaoImpl";

List<ElecSystemDdlForm> findKeyWords();
}
