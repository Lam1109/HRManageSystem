package com.hr.dao;

import com.hr.bean.Depart;
import java.util.List;

public interface DepartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table depart
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer departId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table depart
     *
     * @mbggenerated
     */
    int insert(Depart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table depart
     *
     * @mbggenerated
     */
    Depart selectByPrimaryKey(Integer departId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table depart
     *
     * @mbggenerated
     */
    List<Depart> selectAll(Depart depart);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table depart
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Depart record);
}