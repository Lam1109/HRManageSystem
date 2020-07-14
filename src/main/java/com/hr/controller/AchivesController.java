package com.hr.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.hr.bean.Achives;
import com.hr.bean.ResultObject;
import com.hr.service.IAchivesService;
import com.hr.util.Constant;

@RestController
@RequestMapping("/achives")
public class AchivesController {
	@Autowired
	private IAchivesService achivesService;
	@RequestMapping("/getAll")
	public ResultObject<List<Achives>> getAllAchives(@RequestParam("limit") int limit,@RequestParam("page") int page,Achives achives) {
		//获取数据
		PageInfo<Achives> pageInfo=achivesService.getAllAchives(limit,page,achives);
		//去后台取数据
		List<Achives> list=pageInfo.getList();
		//定义一个返回对象
		ResultObject<List<Achives>> result=new ResultObject<List<Achives>>();
		//赋值
		result.setCode(Constant.SUCCESS_RETUEN_CODE);
		result.setMsg("查询成功");
		result.setData(list);
		result.setCount(pageInfo.getTotal());
		return result;
	}
	@RequestMapping("/addAchives")
	public ResultObject<Integer> addUser(Achives achives){
		ResultObject<Integer> rs=new ResultObject<Integer>();
		achives.setEmpStatus(1);
		Integer total=achivesService.addAchives(achives);
		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
		rs.setMsg("增加成功");
		rs.setData(total);
		return rs;
	} 
	@RequestMapping("/updateAchives")
	public ResultObject<Integer> updateAchives(Achives achives){
		ResultObject<Integer> rs=new ResultObject<Integer>();
		Integer total=achivesService.updateAchives(achives);
		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
		rs.setMsg("修改成功");
		rs.setData(total);
		return rs;
	}
	/**
	 * 更新档案状态
	 * @param achives
	 * @return
	 */
	@RequestMapping("/updateAchivesStatus")
	public ResultObject<Integer> updateAchivesStatus(Achives achives){
		ResultObject<Integer> rs=new ResultObject<Integer>();
		Integer total=achivesService.updateAchivesStatus(achives);
		rs.setCode(Constant.SUCCESS_RETUEN_CODE);
		rs.setMsg("修改成功");
		rs.setData(total);
		return rs;
	}
	
	@RequestMapping("/getAllToSelect")
	public ResultObject<List<Achives>> getAllToSelect() {
		ResultObject<List<Achives>> result=new ResultObject<List<Achives>>();
		List<Achives> list=achivesService.getAllToSelect();
		result.setCode(Constant.SUCCESS_RETUEN_CODE);
		result.setMsg("查询成功");
		result.setData(list);
		result.setCount(list.size());
		return result;
	}	
}
