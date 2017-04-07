package cn.itcast.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.TimeLineDao;
import cn.itcast.dao.UserDao;
import cn.itcast.entity.TimeLine;
import cn.itcast.entity.User;
import cn.itcast.searchvo.SearchVO;

@Transactional
@Service("timeLineService")
public class TimeLineServiceImpl implements TimeLineService {

	@Autowired
	private TimeLineDao timeLineDao;
	@Autowired
	private UserDao userDao;

	public void save(String content) {
		TimeLine timeLine = new TimeLine();
		timeLine.setContent(content);
		Date date = new Date();
		timeLine.setLogTime(date);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		timeLine.setUsername(user.getUsername());
		timeLine.setRecordStatus("A");
		timeLineDao.save(timeLine);

	}

	public List<TimeLine> getAllTimeLineByUserame(String username) {
		return timeLineDao.getAllTimeLineByUserame(username);
	}

	public List<TimeLine> getTimeLineyBySearchVO(SearchVO searchVO) {
		List<TimeLine> list = timeLineDao.getTimeLine(searchVO);

		return list;
	}

	public void deleteTimeLineById(String userId) {
		timeLineDao.deleteTimeLineById(userId);
	}

	public Map findOnePage(int offset, int pagesize,SearchVO searchVO) {
		Map map = timeLineDao.findOnePage(offset, pagesize,searchVO);
		
		return map;
	}
	
	public List<TimeLine> getAllTimeLine() {
		List<TimeLine> list = timeLineDao.getAllTimeLine();
		
		return list;
	}
	
	public List<String> findAllTineLineUsername(){
		List<String> list = timeLineDao.findAllTineLineUsername();
		
		return list;
	}

}
