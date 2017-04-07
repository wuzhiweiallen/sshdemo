package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.TimeLine;
import cn.itcast.entity.User;

public interface UserService {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username,String password);
	/**
	 * 
	 * @param user
	 */
	public void register(User user);
	/**
	 * 
	 * @param user
	 */
	public void updateUser(User user);
	
}
