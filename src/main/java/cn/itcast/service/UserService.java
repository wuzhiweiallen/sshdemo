package cn.itcast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.UserDaoImpl;
import cn.itcast.entity.User;

@Transactional
@Service("userService")
public class UserService {

	@Autowired
	private UserDao userDao;
	

	public boolean login(String username,String password){;
	
		User user = userDao.login(username,password);
		if(user != null){
			return true;
		}
		return false;	
	}
}
