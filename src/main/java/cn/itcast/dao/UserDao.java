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
	/**
	 * 
	 * @param user
	 */
	public void save (User user);
	/**
	 * 
	 * @param user
	 */
	public void updateUser(User user);
	
}
