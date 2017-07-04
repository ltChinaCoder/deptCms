package cn.itcast.elec.Dao.Impl;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.Dao.IElecLogDao;
import cn.itcast.elec.domain.ElecLog;
@Repository(IElecLogDao.service_name)
public class ElecLogDaoImpl extends CommonDaoImpl<ElecLog> implements IElecLogDao{
}
