package cn.itcast.dao;

import cn.itcast.entity.User;

public interface UserDao {

	public User login(String username,String password);
}
