package cn.itcast.elec.Dao.Impl;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.Dao.IElecCommonMsgDao;
import cn.itcast.elec.domain.ElecCommonMsg;
@Repository(IElecCommonMsgDao.service_name)
public class ElecCommonMsgDaoImpl extends CommonDaoImpl<ElecCommonMsg> implements IElecCommonMsgDao<ElecCommonMsg>{

}
