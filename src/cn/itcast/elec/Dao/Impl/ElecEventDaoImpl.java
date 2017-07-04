package cn.itcast.elec.Dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.itcast.elec.Dao.IElecEventDao;
import cn.itcast.elec.domain.ElecEvent;
import cn.itcast.elec.web.form.ElecEventForm;

@Repository(IElecEventDao.service_name)
public class ElecEventDaoImpl extends CommonDaoImpl<ElecEvent> implements IElecEventDao {

	@Override
	public long getTotalScore(StringBuffer hqlWhere) {

		final String hql = "select sum(score) from  ElecEvent " + hqlWhere.toString();
		  Long uniqueResult = (Long)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		if (uniqueResult == null) {
			return 0;
		} else {
			return uniqueResult;
		}
	}

}
