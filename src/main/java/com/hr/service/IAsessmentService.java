package com.hr.service;

import com.github.pagehelper.PageInfo;
import com.hr.bean.Asessment;

/**
* 服务层
* @author hr
*
*/	
public interface IAsessmentService {
	/**
	 * @param limit
	 * @param page
	 * @param Asessment
	 * @return
	 */
	public PageInfo<Asessment>getAllAsessment(int limit,int page,Asessment asessment);
	/**
	 * @param Asessment
	 * @return
	 */
	public Integer addAsessment(Asessment asessment);
	/**
	 * @param Asessment
	 * @return
	 */
	public Integer updateAsessment(Asessment asessment);
	
    /**
     * 更新状态
     * @param record
     * @return
     */
    public int updateStatusByPoJo(Asessment record);
}
