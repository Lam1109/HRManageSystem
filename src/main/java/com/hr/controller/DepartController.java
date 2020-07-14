package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.hr.bean.Depart;
import com.hr.bean.ResultObject;
import com.hr.service.IDepartService;
import com.hr.util.Constant;

@RestController
@RequestMapping("/depart")
public class DepartController {
	@Autowired
	private IDepartService departService;
	@RequestMapping("/getAll")
	public ResultObject<List<Depart>> getAllDepart(@RequestParam("limit") int limit,@RequestParam("page") int page,Depart depart) {
		//获取数据
		PageInfo<Depart> pageInfo=departService.getAllDepart(limit,page,depart);
		//去后台取数据
		List<Depart> list=pageInfo.getList();
		//定义一个返回对象
		ResultObject<List<Depart>> result=new ResultObject<List<Depart>>();
		//赋值
		result.setCode(Constant.SUCCESS_RETUEN_CODE);
		result.setMsg("查询成功");
		result.setData(list);
		result.setCount(pageInfo.getTotal());
		return result;
	}
	@RequestMapping("/addDepart")
	public ResultObject<Integer> addDepart(Depart depart){
		ResultObject<Integer> rs=new ResultObject<Integer>();
		Integer total=departService.addDepart(depart);
		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
		rs.setMsg("增加成功");
		rs.setData(total);
		return rs;
	} 
	@RequestMapping("/updateDepart")
	public ResultObject<Integer> updateDepart(Depart depart){
		ResultObject<Integer> rs=new ResultObject<Integer>();
		Integer total=departService.updateDepart(depart);
		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
		rs.setMsg("修改成功");
		rs.setData(total);
		return rs;
	}
	
	/**
	 * 部门选择下拉框
	 * @return
	 */
	@RequestMapping("/getAlldepartSelect")
	public ResultObject<List<Depart>> getAlldepartSelect(){
		 ResultObject<List<Depart>> rs=new  ResultObject<List<Depart>>();
		 List<Depart> list=departService.getAlldepartSelect();
		 rs.setCode(Constant.SUCCESS_RETUEN_CODE);
		 rs.setMsg("修改成功");
		 rs.setData(list);
		 rs.setCount(list.size());
		 return rs;
			
	}
}
