package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.TimeLine;

public interface UserService {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username,String password);
	
}
