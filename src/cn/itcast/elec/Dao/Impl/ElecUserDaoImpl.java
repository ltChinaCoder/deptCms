package cn.itcast.elec.Dao.Impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import cn.itcast.elec.Dao.IElecUserDao;
import cn.itcast.elec.domain.ElecUser;
@Repository(IElecUserDao.service_name)
public class ElecUserDaoImpl extends CommonDaoImpl<ElecUser> implements IElecUserDao{

	@Override
	public List<Object[]> findUserByChart() {
		final String hql="select ddlName as jctName,count(*) as jctCount from elec_user  left outer join elec_systemDdl "+
	"on jctid=ddlcode and keyword='ѧԺ' group by jctid";
	return 	sessionFactory.getCurrentSession().createSQLQuery(hql).addScalar("jctName",Hibernate.STRING).addScalar("jctCount",Hibernate.STRING).list();
	}

}
