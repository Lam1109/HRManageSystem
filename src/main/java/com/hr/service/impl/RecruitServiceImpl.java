package com.hr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hr.bean.Recruit;
import com.hr.dao.RecruitMapper;
import com.hr.service.IRecruitService;
/**
 * 招聘服务实现层
 * @author hr
 *
 */
@Service
public class RecruitServiceImpl implements IRecruitService {

	/**
	 * 招聘数据层接口
	 */
	@Autowired
	private RecruitMapper recruitMapper;
	/**
	 * 查询所有招聘
	 */
	@Override
	public PageInfo<Recruit> getAllRecruit(int limit, int page, Recruit recruit) {
		//开启分页
        PageHelper.startPage(page, limit);
		//查询数据
		List< Recruit> list=recruitMapper.selectAll(recruit);
		PageInfo< Recruit> pageInfo=new PageInfo< Recruit>(list);
		return pageInfo;
	}
	/**
	 * 增加招聘
	 * @return 整型
	 */
	@Override
	public Integer addRecruit(Recruit recruit) {
		return   recruitMapper.insert( recruit);
	}
	/**
	 * 修改招聘
	 * @return 整型
	 */
	@Override
	public Integer updateRecruit(Recruit recruit) {
		return recruitMapper.updateByPrimaryKey(recruit);
	}

}
