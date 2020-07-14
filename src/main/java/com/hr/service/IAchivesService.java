package com.hr.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hr.bean.Achives;

public interface IAchivesService {
	/**
	 * @param limit
	 * @param page
	 * @param Achives
	 * @return
	 */
	public PageInfo<Achives>getAllAchives(int limit,int page,Achives achives);
	/**
	 * @param Achives
	 * @return
	 */
	public Integer addAchives(Achives achives);
	/**
	 * @param Achives
	 * @return
	 */
	public Integer updateAchives(Achives achives);
	
	
	/**
	 * @param Achives
	 * @return
	 */
	public Integer updateAchivesStatus(Achives achives);
	
	/**
	 * @return
	 */
	public List<Achives> getAllToSelect();


}
