package com.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hr.bean.ResultObject;
import com.hr.bean.User;
import com.hr.service.IUserService;
import com.hr.util.Constant;

/**
 * 用户接口层
 * @author hr
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	/**
	 * 用户服务层代码
	 */
	@Autowired
	private IUserService userService;
	/**
	 * @return
	 */
	@RequestMapping("/getAll")
	public ResultObject<List<User>> getAllUser(@RequestParam("limit") int limit,@RequestParam("page") int page,User user) {
		//获取数据
		PageInfo<User> pageInfo=userService.getAllUser(limit,page,user);
		//去后台取数据
		List<User> list=pageInfo.getList();
		//定义一个返回对象
		ResultObject<List<User>> result=new ResultObject<List<User>>();
		//赋值
		result.setCode(Constant.SUCCESS_RETUEN_CODE);
		result.setMsg("查询成功");
		result.setData(list);
		result.setCount(pageInfo.getTotal());
		return result;
	}
	/**
	 * 增加用户信息
	 * @param user 用户添加
	 * @return
	 */
	@RequestMapping("/addUser")
	public ResultObject<Integer> addUser(User user){
		ResultObject<Integer> rs=userService.addUser(user);
		return rs;
	} 
	/**
	 * 登录接口
	 * @param user 用户信息
	 * @param request 请求
	 * @return
	 */
	@RequestMapping("/login")
	public ResultObject<User> login(User user,HttpServletRequest request){
		//定义返回值
		ResultObject<User> rs=new ResultObject<User>();
		//登录验证
		User info=userService.login(user);
		//如果查询失败，则没有该用户
		if(null==info) {
			rs.setCode(Constant.FAILURE_RETUEN_CODE);
			rs.setMsg("用户不存在");
		}else {
			rs.setCode(Constant.SUCCESS_RETUEN_CODE);
			rs.setMsg("查询成功");
			rs.setData(info);
			//往session赋值
			request.getSession().setAttribute("user", user);
		}
		return rs;
	}
	/**
	 * 退出登录接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginOut")
	public ResultObject<Integer> loginOut(HttpServletRequest request){
		ResultObject<Integer> rs=new ResultObject<Integer>();
		request.getSession().removeAttribute("user");
		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
		rs.setMsg("退出成功");
		return rs;
	}
	/**
	 * 锁定账号
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateUser")
	public ResultObject<Integer> updateUser(User user){
		int total=userService.updateUser(user);
		ResultObject<Integer> rs=new ResultObject<Integer>();
		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
		rs.setMsg("更新成功");
		rs.setData(total);
		return rs;
	}
}
