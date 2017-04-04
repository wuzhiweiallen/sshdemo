package cn.itcast.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.itcast.entity.User;
import cn.itcast.service.UserService;
import cn.itcast.service.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

@Controller("userAction")
public class UserAction extends ActionSupport {

	private User user;
	
	@Autowired
	private UserService userService;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String login() throws Exception {
		boolean loginFlag = userService.login(user.getUsername(), user.getPassword());
		if(!loginFlag)
			return ERROR;
		
		return SUCCESS;
	}
	
}
