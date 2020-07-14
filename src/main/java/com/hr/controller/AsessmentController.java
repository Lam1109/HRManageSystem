package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hr.bean.Asessment;
import com.hr.bean.ResultObject;
import com.hr.service.IAsessmentService;
import com.hr.util.Constant;
@RestController
@RequestMapping("/asessment")

public class AsessmentController {
	
		/**
		 * 工资服务层代码
		 */
		@Autowired
		private IAsessmentService asessmentService;
		@RequestMapping("/getAll")
		public ResultObject<List<Asessment>> getAllAsessment(@RequestParam("limit") int limit,@RequestParam("page") int page,Asessment asessment) {
			//获取数据
			PageInfo<Asessment> pageInfo=asessmentService.getAllAsessment(limit,page,asessment);
			//去后台取数据
			List<Asessment> list=pageInfo.getList();
			//定义一个返回对象
			ResultObject<List<Asessment>> result=new ResultObject<List<Asessment>>();
			//赋值
			result.setCode(Constant.SUCCESS_RETUEN_CODE);
			result.setMsg("查询成功");
			result.setData(list);
			result.setCount(pageInfo.getTotal());
			return result;
		}
		
		@RequestMapping("/addAsessment")
		public ResultObject<Integer> addAsessment(Asessment asessment){
			ResultObject<Integer> rs=new ResultObject<Integer>();
			Integer total=asessmentService.addAsessment(asessment);
			rs.setCode(Constant.SUCCESS_RETUEN_CODE);
			rs.setMsg("增加成功");
			rs.setData(total);
			return rs;
		} 
		@RequestMapping("/updateAsessment")
		public ResultObject<Integer> updateAsessment(Asessment asessment){
			ResultObject<Integer> rs=new ResultObject<Integer>();
			Integer total=asessmentService.updateAsessment(asessment);
			rs.setCode(Constant.SUCCESS_RETUEN_CODE);
			rs.setMsg("修改成功");
			rs.setData(total);
			return rs;
		}
		@RequestMapping("/updateStatusByPoJo")
		public ResultObject<Integer> updateStatusByPoJo(Asessment asessment){
			ResultObject<Integer> rs=new ResultObject<Integer>();
			Integer total=asessmentService.updateStatusByPoJo(asessment);
			rs.setCode(Constant.SUCCESS_RETUEN_CODE);
			rs.setMsg("修改状态成功");
			rs.setData(total);
			return rs;
		}
		
}
