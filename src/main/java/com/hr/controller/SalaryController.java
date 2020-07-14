package com.hr.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.hr.bean.ResultObject;
import com.hr.bean.Salary;
import com.hr.service.ISalaryService;
import com.hr.util.Constant;
/**
 * 工资接口层
 * @author hr
 *
 */
@RestController
@RequestMapping("/salary")
public class SalaryController {
	/**
	 * 工资服务层代码
	 */
	@Autowired
	private ISalaryService salaryService;
	@RequestMapping("/getAll")
	public ResultObject<List<Salary>> getAllSalary(@RequestParam("limit") int limit,@RequestParam("page") int page,Salary salary) {
		//获取数据
		PageInfo<Salary> pageInfo=salaryService.getAllSalary(limit,page,salary);
		//去后台取数据
		List<Salary> list=pageInfo.getList();
		//定义一个返回对象
		ResultObject<List<Salary>> result=new ResultObject<List<Salary>>();
		//赋值
		result.setCode(Constant.SUCCESS_RETUEN_CODE);
		result.setMsg("查询成功");
		result.setData(list);
		result.setCount(pageInfo.getTotal());
		return result;
	}
	@RequestMapping("/addSalary")
	public ResultObject<Integer> addUser(Salary salary){
		return salaryService.addSalary(salary);
	} 
	@RequestMapping("/updateSalary")
	public ResultObject<Integer> updateSalary(Salary salary){
		ResultObject<Integer> rs=new ResultObject<Integer>();
		Integer total=salaryService.updateSalary(salary);
		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
		rs.setMsg("修改成功");
		rs.setData(total);
		return rs;
	}
}
