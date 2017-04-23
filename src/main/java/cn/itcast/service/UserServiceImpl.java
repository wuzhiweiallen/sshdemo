package cn.itcast.service;

import javax.servlet.ServletContext;
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
			ServletContext servletContext = ServletActionContext.getServletContext();
			String usernameServletContext = (String) servletContext.getAttribute(user.getUsername());
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User userSession = (User) session.getAttribute("user");
			//userSession为空 ，usernameServletContext存在值 说明还没有登录 就验证是否重复登录
			if(userSession == null && usernameServletContext!=null){
				//验证重复登录 session存在 usernameServletContext也存在 说明这个用户正在某一浏览器登录 。
				//其他人在使用这个号登录就不行了
				if(usernameServletContext != null && !usernameServletContext.isEmpty()){
					if(usernameServletContext.equals(user.getUsername())){
						request.setAttribute("errorMessage", "this user has already loged in.");
						return false;
					}
				}
			}
			session.setAttribute("user", user);
			request.setAttribute("username", user.getUsername());
			servletContext.setAttribute(user.getUsername(), user.getUsername());
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
