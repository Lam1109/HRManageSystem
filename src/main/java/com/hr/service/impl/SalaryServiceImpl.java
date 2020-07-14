package com.hr.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.bean.ResultObject;
import com.hr.bean.Salary;
import com.hr.dao.AsessmentMapper;
import com.hr.dao.SalaryMapper;
import com.hr.service.ISalaryService;
import com.hr.util.Constant;
/**
 * 工资管理服务层实现
 * @author hr
 *
 */
@Service
public class SalaryServiceImpl implements ISalaryService {
	/**
	 * 工资数据层接口
	 */
	@Autowired
	private SalaryMapper salaryMapper;
	
	@Autowired 
	AsessmentMapper asessmentMapper;
	
	/**
	 * 查询所工资
	 */
	public PageInfo<Salary> getAllSalary(int limit,int page,Salary salary){
		//开启分页
		PageHelper.startPage(page, limit);
		//查询数据
		List<Salary> list=salaryMapper.selectAll(salary);
		PageInfo<Salary> pageInfo=new PageInfo<Salary>(list);
		return  pageInfo;
	}
	@Override
	public ResultObject<Integer> addSalary(Salary salary) {
		//统一返回值
		ResultObject<Integer> rs=new ResultObject<Integer>();
		//判断是否已经添加过此员工本月薪资
		Integer total=salaryMapper.selectCountByPoJo(salary);
		if(total==null || total==0) {
			//计算考勤扣除薪资
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("empId", salary.getEmpId());
			param.put("salaryMonth", salary.getSalaryMonth());
			//查询扣除薪资
			double checkDeduction=asessmentMapper.selectTotaMoneyByEmpId(param);
			salary.setCheckDeduction(checkDeduction);
			//计算总薪资（注意精度丢失问题）
			BigDecimal baseSalary = new BigDecimal(salary.getBaseSalary());
	        BigDecimal achievements = new BigDecimal(salary.getAchievements());
	        BigDecimal bonus = new BigDecimal(salary.getBonus());
	        BigDecimal checkDeductions = new BigDecimal(checkDeduction);
	        BigDecimal sumSalary=(baseSalary.add(achievements).add(bonus).subtract(checkDeductions));
	        salary.setSumSalary(sumSalary.doubleValue());
			//入库添加
	        StringBuilder sb=new StringBuilder(salary.getSalaryMonth());
			sb.append("-01");
			salary.setSalaryMonth(sb.toString());
			salaryMapper.insert(salary);
			rs.setCode(Constant.SUCCESS_RETUEN_CODE);
			rs.setMsg("增加成功");
			rs.setData(total);
		}else {
			rs.setCode(Constant.HASE_RETUEN_CODE);
			rs.setMsg("此员工本月薪资单已经添加过");
			rs.setData(1);
		}
		//增加工资
		return rs;
	}
	@Override
	public Integer updateSalary(Salary salary) {
		// 修改工资
		//计算总薪资（注意精度丢失问题）
		BigDecimal baseSalary = new BigDecimal(salary.getBaseSalary());
        BigDecimal achievements = new BigDecimal(salary.getAchievements());
        BigDecimal bonus = new BigDecimal(salary.getBonus());
        BigDecimal checkDeductions = new BigDecimal(salary.getCheckDeduction());
        BigDecimal sumSalary=(baseSalary.add(achievements).add(bonus).subtract(checkDeductions));
        salary.setSumSalary(sumSalary.doubleValue());
		//入库添加
        StringBuilder sb=new StringBuilder(salary.getSalaryMonth());
		sb.append("-01");
		salary.setSalaryMonth(sb.toString());
		return salaryMapper.updateByPrimaryKey(salary);
	}
	
	
}
