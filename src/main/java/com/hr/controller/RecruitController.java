package com.hr.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.hr.bean.Recruit;
import com.hr.bean.ResultObject;
import com.hr.service.IRecruitService;
import com.hr.util.Constant;

@RestController
@RequestMapping("/recruit")
public class RecruitController {
	@Autowired
	private IRecruitService recruitService;
	@RequestMapping("/getAll")
	public ResultObject<List<Recruit>> getAllRecruit(@RequestParam("limit") int limit,@RequestParam("page") int page,Recruit recruit) {
		//获取数据
		PageInfo<Recruit> pageInfo=recruitService.getAllRecruit(limit,page,recruit);
		//去后台取数据
		List<Recruit> list=pageInfo.getList();
		//定义一个返回对象
		ResultObject<List<Recruit>> result=new ResultObject<List<Recruit>>();
		//赋值
		result.setCode(Constant.SUCCESS_RETUEN_CODE);
		result.setMsg("查询成功");
		result.setData(list);
		result.setCount(pageInfo.getTotal());
		return result;
	}
	@RequestMapping("/addRecruit")
	public ResultObject<Integer> addRecruit(Recruit recruit){
		ResultObject<Integer> rs=new ResultObject<Integer>();
		Integer total=recruitService.addRecruit(recruit);
		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
		rs.setMsg("增加成功");
		rs.setData(total);
		return rs;
	} 
	@RequestMapping("/updateRecruit")
	public ResultObject<Integer> updateRecruit(Recruit recruit){
		ResultObject<Integer> rs=new ResultObject<Integer>();
		Integer total=recruitService.updateRecruit(recruit);
		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
		rs.setMsg("修改成功");
		rs.setData(total);
		return rs;
	}
}
