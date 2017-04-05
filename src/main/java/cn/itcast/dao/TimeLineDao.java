package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.TimeLine;
import cn.itcast.searchvo.SearchVO;

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
	public List<TimeLine> getAllTimeLineByUserame(String username);
	/**
	 * 
	 * @param searchVO
	 * @return
	 */
	public List<TimeLine> getTimeLine(SearchVO searchVO);
	/**
	 * 
	 * @param userId
	 */
	public void deleteTimeLineById(String userId);
	/**
	 * 
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	public List<TimeLine> findOnePage(int offset, int pagesize);
	/**
	 * 
	 * @return
	 */
	public List<TimeLine> getAllTimeLine();
}
