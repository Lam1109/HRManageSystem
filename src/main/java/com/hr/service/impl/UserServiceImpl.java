package com.hr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.bean.ResultObject;
import com.hr.bean.User;
import com.hr.dao.UserMapper;
import com.hr.service.IUserService;
import com.hr.util.Constant;

/**
 * 用户服务层实现
 * @author hr
 *
 */
@Service
public class UserServiceImpl implements IUserService {
	/**
	 * 用户数据层接口
	 */
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 查询所有用户
	 */
	@Override
	public PageInfo<User> getAllUser(int limit,int page,User user) {
		//开启分页
		PageHelper.startPage(page, limit);
		//查询数据
		List<User> list=userMapper.selectAll(user);
		PageInfo<User> pageInfo=new PageInfo<User>(list);
		return pageInfo;
	}
	/**
	 * 增加账号
	 * @return 数据集合
	 */
	public ResultObject<Integer> addUser(User user) {
		User check=new User();
		check.setUserName(user.getUserName());
		List<User> list =userMapper.selectByBean(check);
		ResultObject<Integer> rs=new ResultObject<Integer>();
		if(list.isEmpty()) {
			user.setUserStatus(1);
			Integer total=userMapper.insert(user);
			rs.setCode(Constant.SUCCESS_RETUEN_CODE);
			rs.setMsg("增加成功");
			rs.setData(total);
		}
		else {
			rs.setCode(Constant.HASE_RETUEN_CODE);
			rs.setMsg("账号已存在");
		}
		return rs;
	}
	/**
	 * 用户登录接口
	 * */
	public User login(User user) {
		user.setUserStatus(1);
		return userMapper.login(user);
	}
	
	/**
	 * 锁定账号
	 * @param user
	 * @return
	 */
	public int updateUser(User user){
		return userMapper.updateByPrimaryKey(user);
	}
}
