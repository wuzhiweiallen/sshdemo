package cn.itcast.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.UserDao;
import cn.itcast.entity.User;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public boolean login(String username,String password){
		User user = userDao.login(username,password);
		if(user != null){
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.setAttribute("username", user.getUsername());
			session.setAttribute("imagepath", user.getImagePath());
			return true;
		}
		
		return false;	
	}
	
	public void register(User user){
		userDao.save(user);
	}
	
	public void updateUser(User user){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		if(user!=null && (user.getImagePath() == null || user.getImagePath().isEmpty())){
			user.setImagePath(sessionUser.getImagePath()); 
		}
		user.setUid(sessionUser.getUid());
		userDao.updateUser(user);
	}
	
}
