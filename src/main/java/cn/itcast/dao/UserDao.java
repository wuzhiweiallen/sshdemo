package cn.itcast.dao;

import cn.itcast.entity.User;

public interface UserDao {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username,String password);
	
}
