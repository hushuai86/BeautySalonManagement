package com.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.HairdressingReservationMapper;
import com.java.pojo.HairdressingReservation;
import com.java.service.HairdressingReservationService;

@Service("HairdressingReservationService")
public class HairdressingReservationServiceImpl implements HairdressingReservationService {
	@Autowired
	private HairdressingReservationMapper hairdressingReservationMapper;

	@Override
	public int selectEvaluate(Map<String, Object> map) {
		return hairdressingReservationMapper.selectEvaluate(map);
	}

	@Override
	public List<Map<String, Object>> selectAllReservation(HairdressingReservation hairdressingReservation,
			Map<String, Object> map) {
		return hairdressingReservationMapper.selectAllReservation(hairdressingReservation, map);
	}

	@Override
	public void addeServation(HairdressingReservation hairdressingReservation) {
		hairdressingReservationMapper.addeServation(hairdressingReservation);
	}

	@Override
	public void updateByPrimaryKeySelective(HairdressingReservation hairdressingReservation) {
		hairdressingReservationMapper.updateByPrimaryKeySelective(hairdressingReservation);
	}

	@Override
	public List<Map<String, Object>> selectAllCondition(Map<String, Object> map) {
		return hairdressingReservationMapper.selectAllCondition(map);
	}

	@Override
	public void updateStatus(Map<String, Object> map) {
		hairdressingReservationMapper.updateStatus(map);
	}

	@Override
	public HairdressingReservation selectByPrimaryKey(Integer id) {
		return hairdressingReservationMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer selectSunPriceByLoginId(String loginId) {
	  Integer price = hairdressingReservationMapper.selectSunPriceByLoginId(loginId);
	  if(price==null){
		  return 0;
	  }
	  return price;
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		hairdressingReservationMapper.deleteByPrimaryKey(id);
		
	}
	@Override
	public List<HairdressingReservation> selectReservationsByCode(String code) {
	    return hairdressingReservationMapper.selectReservationsByCode(code);
	}

	@Override
	public List<HairdressingReservation> selectAllReservationsByDay(String day) {
	    return hairdressingReservationMapper.selectAllReservationsByDay(day);
	}
}
