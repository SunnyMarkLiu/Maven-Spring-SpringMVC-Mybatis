package com.markliu.mybatis_advance.mapper;

import com.markliu.mybatis_advance.domain.po.User;

import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-18 下午5:49
 */
public interface UserMapper {

	void insertUser(User user) throws Exception;
	User selectUserById(int id) throws Exception;
	List<User> selectUserAndItems() throws Exception;
}
