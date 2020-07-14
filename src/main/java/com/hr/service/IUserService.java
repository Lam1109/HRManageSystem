package com.hr.service;

import com.github.pagehelper.PageInfo;
import com.hr.bean.ResultObject;
import com.hr.bean.User;


/**
 * 用户服务层
 * @author hr
 *
 */
public interface IUserService {
	/**
	 * 查询所有管理员
	 * @return 数据集合
	 */
	public PageInfo<User> getAllUser(int limit,int page,User user);
	
	/**
	 * 增加账号
	 * @return 数据集合
	 */
	public ResultObject<Integer> addUser(User user);

	/**
	 * 用户login
	 * @return 数据集合
	 */
	public User login(User user);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int updateUser(User user);
}
