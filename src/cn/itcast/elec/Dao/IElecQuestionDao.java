package cn.itcast.elec.Dao;

import cn.itcast.elec.domain.ElecQuestion;

public interface IElecQuestionDao extends ICommonDao<ElecQuestion>{
  static String service_name="cn.itcast.elec.Dao.Impl.ElecQuestionDaoImpl";
}
