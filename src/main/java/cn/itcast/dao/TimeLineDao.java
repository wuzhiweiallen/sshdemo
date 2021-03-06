package cn.itcast.dao;

import java.util.List;
import java.util.Map;

import cn.itcast.entity.Comments;
import cn.itcast.entity.SubComments;
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
	public void deleteTimeLineById(int id);
	/**
	 * 
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	public Map findOnePage(int offset, int pagesize, SearchVO searchVO);
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
	/**
	 * 
	 * @param comments
	 */
	public void saveComments(Comments comments);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public TimeLine getTimeLineById(int id);
	/**
	 * 
	 * @param subComments
	 */
	public void saveSubComments(SubComments subComments);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Comments getSubCommentsById(int id);
}
