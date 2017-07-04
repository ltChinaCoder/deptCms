package cn.itcast.elec.Dao.Impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cn.itcast.elec.Dao.ICommonDao;
import cn.itcast.elec.util.GenicSuperClass;
import cn.itcast.elec.util.PageInfo;

public class CommonDaoImpl<T> implements ICommonDao<T> {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(T t) {
		sessionFactory.getCurrentSession().save(t);
	}

	@Override
	public void updateObject(T t) {
		sessionFactory.getCurrentSession().update(t);
	}

	@Override
	public T findObjectById(String textID) {

		Class entity1 = GenicSuperClass.getClass(this.getClass());
		@SuppressWarnings("unchecked")
		T t = (T) sessionFactory.getCurrentSession().get(entity1, textID);
		return t;
	}

	@Override
	public void deleteObjectById(T et) {
		sessionFactory.getCurrentSession().delete(et);
	}

	@Override
	public void deleteObjectsByIds(String... textids) {
		Class entity = GenicSuperClass.getClass(this.getClass());
		for (int i = 0; i < textids.length; i++) {
			T t = (T) sessionFactory.getCurrentSession().get(entity, textids[i]);
			sessionFactory.getCurrentSession().delete(t);
		}

	}

	@Override
	public void deleteObjectsByCollection(Collection list) {
		// Class entity=GenicSuperClass.getClass(this.getClass());
		for (Object object : list) {
			sessionFactory.getCurrentSession().delete(object);
		}

	}

	@Override
	public List<T> findByContionsNoPage(String hqlWhere, LinkedHashMap<String, String> orders) {
		if (orders != null) {
			for (Map.Entry<String, String> map : orders.entrySet()) {
				hqlWhere += map.getKey() + " " + map.getValue() + ",";
			}
			hqlWhere = hqlWhere.substring(0, hqlWhere.length() - 1);
		}
		Class entity = GenicSuperClass.getClass(this.getClass());
		final String hql = " from " + entity.getSimpleName() + " " + hqlWhere;
		return sessionFactory.getCurrentSession().createQuery(hql).list();

	}

	@Override
	public void saveByCollection(Collection<T> list) {
		for (T t : list) {
			sessionFactory.getCurrentSession().saveOrUpdate(t);
		}
	}

	@Override
	public List<T> findByContionsWithPage(String hqlWhere, LinkedHashMap<String, String> orders,
			final PageInfo pageInfo) {

		if (orders != null) {
			for (Map.Entry<String, String> map : orders.entrySet()) {
				hqlWhere += map.getKey() + " " + map.getValue() + ",";
			}
			hqlWhere = hqlWhere.substring(0, hqlWhere.length() - 1);
		}
		Class entity = GenicSuperClass.getClass(this.getClass());
		final String hql = " from " + entity.getSimpleName() + " " + hqlWhere;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		pageInfo.setTotalResult(query.list().size());
		query.setFirstResult(pageInfo.getBeginResult());
		query.setMaxResults(pageInfo.getPageSize());
		return query.list();

	}
}
