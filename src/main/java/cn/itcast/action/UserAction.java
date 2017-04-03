package cn.itcast.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.entity.User;
import cn.itcast.service.UserService;

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

	@Override
	public String execute() throws Exception {
		boolean loginFlag = userService.login(user.getUsername(), user.getPassword());
		if(!loginFlag)
			return "LOGINFAIL";
		return "LOGINSUCCESS";
	}
}
