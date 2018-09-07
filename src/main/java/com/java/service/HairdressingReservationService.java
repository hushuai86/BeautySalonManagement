package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.pojo.HairdressingReservation;

public interface HairdressingReservationService {
	
	public int selectEvaluate(Map<String, Object> map);

	public List<Map<String, Object>> selectAllReservation(HairdressingReservation hairdressingReservation,Map<String,Object> map);
	
	public void addeServation(HairdressingReservation hairdressingReservation);
	
	public void updateByPrimaryKeySelective(HairdressingReservation hairdressingReservation);
	
	public List<Map<String, Object>> selectAllCondition(Map<String,Object> map);
	
	public void updateStatus(Map<String,Object> map);
	
	public HairdressingReservation selectByPrimaryKey(Integer id);

	public Integer selectSunPriceByLoginId(String loginId);

	public void deleteByPrimaryKey(Integer id);

	public List<HairdressingReservation> selectAllReservationsByDay(String day);

	List<HairdressingReservation> selectReservationsByCode(String code);
}
