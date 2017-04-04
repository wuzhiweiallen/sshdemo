package cn.itcast.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.itcast.entity.TimeLine;

@Repository("timeLineDao")
public class TimeLineDaoImpl implements TimeLineDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public void save(TimeLine timeLine) {
		this.getCurrentSession().save(timeLine);
		
	}
	
	public List<TimeLine> getAllTimeLineByUserId(String username){
		SQLQuery query = this.getCurrentSession().createSQLQuery("select * from TimeLine where username = ? order by logTime desc").addEntity(TimeLine.class);
		query.setParameter(0, username);
		List<TimeLine> timeLineList = query.list();
		return timeLineList;
	}

}
