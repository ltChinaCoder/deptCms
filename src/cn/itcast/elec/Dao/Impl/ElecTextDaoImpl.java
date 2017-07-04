package cn.itcast.elec.Dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.Dao.IElecTextDao;
import cn.itcast.elec.domain.ElecText;
@Repository(IElecTextDao.Service_Name)
public class ElecTextDaoImpl extends CommonDaoImpl<ElecText> implements IElecTextDao<ElecText>{


}
