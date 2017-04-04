package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.TimeLine;

public interface TimeLineService {
	
	/**
	 * 
	 * @param content
	 */
	public void save(String content);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<TimeLine> getAllTimeLineByUserId(String username);

}
