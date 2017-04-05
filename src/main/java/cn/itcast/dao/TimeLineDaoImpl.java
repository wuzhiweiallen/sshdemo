package cn.itcast.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.itcast.entity.TimeLine;
import cn.itcast.searchvo.SearchVO;

@Repository("timeLineDao")
public class TimeLineDaoImpl implements TimeLineDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void save(TimeLine timeLine) {
		this.getCurrentSession().save(timeLine);

	}

	public List<TimeLine> getAllTimeLineByUserame(String username) {
		SQLQuery query = this
				.getCurrentSession()
				.createSQLQuery(
						"select * from timeline where username = ? order by logTime desc")
				.addEntity(TimeLine.class);
		query.setParameter(0, username);
		List<TimeLine> timeLineList = query.list();
		
		return timeLineList;
	}

	@SuppressWarnings("unchecked")
	public List<TimeLine> getTimeLine(SearchVO searchVO) {
		StringBuffer sql = new StringBuffer("select * from timeline where 1=1 ");
		if (searchVO != null) {
			if (StringUtils.isNotEmpty(searchVO.getUsername())) {
				sql.append("and username = '" + searchVO.getUsername()+"'");
			}
			if (StringUtils.isNotEmpty(searchVO.getContent())) {
				sql.append("and content like '%" + searchVO.getContent()+"%'");
			}
			if(StringUtils.isNotEmpty(searchVO.getDatetimeStart()) && StringUtils.isNotEmpty(searchVO.getDatetimeEnd())){
				sql.append("and logTime between '"+searchVO.getDatetimeStart()+"' and DATE_ADD('"+searchVO.getDatetimeEnd()+"',INTERVAL 1 DAY)");
			}
		}
		sql.append(" order by logTime desc");
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql.toString())
				.addEntity(TimeLine.class);
		List<TimeLine> list = query.list();

		return list;
	}
	
	public TimeLine getTimeLineByUserId(String userId){
		SQLQuery query = this
				.getCurrentSession()
				.createSQLQuery(
						"select * from timeline where userId = ?")
				.addEntity(TimeLine.class);
		query.setParameter(0, userId);
		TimeLine timeLine = (TimeLine) query.uniqueResult();
		
		return timeLine;
	}
	
	public void deleteTimeLineById(String userId){
		TimeLine timeLine = getTimeLineByUserId(userId);
		this.getCurrentSession().delete(timeLine);
	}
	
	public List<TimeLine> getAllTimeLine() {
		SQLQuery query = this.getCurrentSession().createSQLQuery("select * from timeline order by logTime desc").addEntity(TimeLine.class);
		List<TimeLine> list = query.list();
		return list;
	}
	
	/**
	 * 获得当前页的结果集 offset 当前页 pagesize 每页显示的条数
	 * 
	 * @return
	 */
	public List<TimeLine> findOnePage(int offset, int pagesize) {
		String sql = "from TimeLine";
		List<TimeLine> lst = null;
		try {
			Query query = this.getCurrentSession().createQuery(sql);
			if (offset != 0 && pagesize != 0) {
				// 从第(offset - 1) * pagesize条记录开始
				query.setFirstResult((offset - 1) * pagesize);
				// 取出pagesize条记录
				/*
				 * 上面两条语句是hibernate中封装好了的，相当于mysql中的 limit offset - 1) *
				 * pagesize pagesize
				 * （例：从query.setFirstResult(0);query.setMaxResults
				 * (8);相当于mysql中limit 0, 8;从第1条开始，取出8条记录。
				 */
				query.setMaxResults(pagesize);
				
			}

			lst = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lst;
	}

}
