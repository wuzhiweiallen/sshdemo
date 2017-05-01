package cn.itcast.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import cn.itcast.entity.TimeLine;
import cn.itcast.searchvo.SearchVO;

public interface TimeLineService {
	
	/**
	 * 
	 * @param timeLineImage
	 * @param content
	 * @param timeLineImageFileName
	 */
	public void save(List<File> timeLineImage,String content,List<String> timeLineImageFileName);
	
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
	public void deleteTimeLineById(int userId);
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
	/**
	 * 
	 * @param curpage
	 * @param searchVO
	 * @return
	 */
	public Map pagination(Integer curpage,SearchVO searchVO);
	/**
	 * 
	 * @param id
	 * @param content
	 */
	public void saveTimeLine(int id,String content);
	/**
	 * 
	 * @param cid
	 * @param content
	 */
	public void saveSubComments(int cid,String content);

}
