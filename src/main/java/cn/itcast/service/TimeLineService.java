package cn.itcast.service;

import java.util.List;
import java.util.Map;

import cn.itcast.entity.TimeLine;
import cn.itcast.searchvo.SearchVO;

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
	public List<TimeLine> getAllTimeLineByUserame(String username);
	/**
	 * 
	 * @param searchVO
	 * @return
	 */
	public List<TimeLine> getTimeLineyBySearchVO(SearchVO searchVO);
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
	public Map findOnePage(int offset, int pagesize,SearchVO searchVO);
	/**
	 * 
	 * @return
	 */
	public List<TimeLine> getAllTimeLine();
	/**
	 * 
	 * @return
	 */
	public List<String> findAllTineLineUsername();

}
