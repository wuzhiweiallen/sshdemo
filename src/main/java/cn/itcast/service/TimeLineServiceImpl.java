package cn.itcast.service;

import java.util.Date;
import java.util.List;

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

	public List<TimeLine> findOnePage(int offset, int pagesize) {
		List<TimeLine> list = timeLineDao.findOnePage(offset, pagesize);
		
		return list;
	}
	
	public List<TimeLine> getAllTimeLine() {
		List<TimeLine> list = timeLineDao.getAllTimeLine();
		
		return list;
	}

}
