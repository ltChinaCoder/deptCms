package cn.itcast.elec.Dao;

import cn.itcast.elec.domain.ElecEvent;

public interface IElecEventDao  extends ICommonDao<ElecEvent>{
 String service_name="cn.itcast.elec.Dao.Impl.ElecEventDaoImpl";
  long getTotalScore(StringBuffer hqlWhere);
}
