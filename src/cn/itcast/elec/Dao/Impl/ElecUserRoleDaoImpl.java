package cn.itcast.elec.Dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.itcast.elec.Dao.IElecUserRoleDao;
import cn.itcast.elec.domain.ElecUserRole;
@Repository(IElecUserRoleDao.service_name)
public class ElecUserRoleDaoImpl extends CommonDaoImpl< ElecUserRole> implements IElecUserRoleDao{

	@Override
	public List<Object[]> findUserByRoleId(final String role) {
		final String sql="select distinct case elec_user_role.roleid when '"+role+"' then '1' else '0' end as flag"+
",elec_user.userid  as userId, elec_user.userName as userName,elec_user.loganName as logonName from elec_user left outer join elec_user_role on elec"+
"_user.userid=elec_user_role.userid and elec_user_role.roleid= '"+role+"' where elec_user."+
"isduty='1'";
		return sessionFactory.getCurrentSession().createSQLQuery(sql).
		addScalar("flag",Hibernate.STRING).	
		addScalar("userId",Hibernate.STRING).	
		addScalar("userName",Hibernate.STRING).	
		addScalar("logonName",Hibernate.STRING).list();		
	}


 
}
