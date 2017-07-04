package cn.itcast.elec.Dao.Impl;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.Dao.IElecQuestionDao;
import cn.itcast.elec.domain.ElecQuestion;
@Repository(IElecQuestionDao.service_name)
public class ElecQuestionDaoImpl extends CommonDaoImpl<ElecQuestion>implements IElecQuestionDao {

}
