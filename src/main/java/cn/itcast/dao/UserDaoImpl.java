package cn.itcast.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.itcast.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public User login(String username,String password) {
		
		@SuppressWarnings("all")
		Query query = this.getCurrentSession().createQuery("from User where username=? and password=?");
		query.setParameter(0, username);
		query.setParameter(1, password);
		List<User> list = query.list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
		
	}

	
}



