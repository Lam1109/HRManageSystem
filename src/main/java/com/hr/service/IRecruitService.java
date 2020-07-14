package com.hr.service;

import com.github.pagehelper.PageInfo;
import com.hr.bean.Recruit;

/**
 * 面试服务层
 * @author hr
 *
 */
public interface IRecruitService {
	/**
	 * 查询所有招聘信息
	 * @return 数据集合
	 */
public PageInfo<Recruit>getAllRecruit(int limit,int page,Recruit recruit);
/**
 * 增加招聘
 * @return 整型
 */
public Integer addRecruit(Recruit recruit);
/**
 * 修改招聘
 * @return 整型
 */
public Integer updateRecruit(Recruit recruit);
}
