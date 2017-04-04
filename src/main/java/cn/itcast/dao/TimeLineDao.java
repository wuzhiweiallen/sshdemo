package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.TimeLine;

public interface TimeLineDao {
	
	/**
	 * 
	 * @param timeLine
	 */
	public void save(TimeLine timeLine);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<TimeLine> getAllTimeLineByUserId(String username);
}
