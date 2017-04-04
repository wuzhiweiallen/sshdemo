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

@Transactional
@Service("timeLineService")
public class TimeLineServiceImpl implements TimeLineService{

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
	
	public List<TimeLine> getAllTimeLineByUserId(String username) {
		return timeLineDao.getAllTimeLineByUserId(username);
	}

}
