package com.hr.service;

import com.github.pagehelper.PageInfo;
import com.hr.bean.ResultObject;
import com.hr.bean.Salary;

/**
* 工资服务层
* @author hr
*
*/	
public interface ISalaryService {
 
	/**
	 * 查询所有工资
	 * @param imit
	 * @param page
	 * @param salary
	 * @return
	 */
	public PageInfo<Salary>getAllSalary(int limit,int page,Salary salary);
	/**
	 * 增加工资
	 * @param salary
	 * @return
	 */
	public ResultObject<Integer>  addSalary(Salary salary);
	/**
	 * 修改工资
	 * @param salary
	 * @return
	 */
	public Integer updateSalary(Salary salary);
	
	
	
	

}
