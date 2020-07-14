package com.hr.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hr.bean.Depart;

/**
* 部门服务层
* @author hr
*
*/	
public interface IDepartService {
/**
 * @param limit
 * @param page
 * @param salary
 * @return
 */
public PageInfo<Depart>getAllDepart(int limit,int page,Depart depart);
/**
 * @param depart
 * @return
 */
public Integer addDepart(Depart depart);
/**
 * @param depart
 * @return
 */
public Integer updateDepart(Depart depart);

/**
 * 部门选择下拉框
 * @return
 */
public List<Depart>getAlldepartSelect();


}
