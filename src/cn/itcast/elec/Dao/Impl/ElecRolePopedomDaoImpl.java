package cn.itcast.elec.Dao.Impl;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.Dao.IElecRolePopedomDao;
import cn.itcast.elec.domain.ElecRolePopedom;

@Repository(IElecRolePopedomDao.service_name)
public class ElecRolePopedomDaoImpl  extends CommonDaoImpl<ElecRolePopedom> implements IElecRolePopedomDao{

}
