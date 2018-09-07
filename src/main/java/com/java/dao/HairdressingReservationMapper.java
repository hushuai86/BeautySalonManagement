package com.java.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.java.pojo.HairdressingReservation;

public interface HairdressingReservationMapper {
    int selectEvaluate(Map<String, Object> map);

    List<Map<String, Object>> selectAllReservation(@Param("h") HairdressingReservation hairdressingReservation,
            @Param("map") Map<String, Object> map);

    int addeServation(HairdressingReservation hairdressingReservation);

    List<Map<String, Object>> selectAllCondition(Map<String, Object> map);

    int updateStatus(Map<String, Object> map);

    int deleteByPrimaryKey(Integer id);

    int insert(HairdressingReservation record);

    int insertSelective(HairdressingReservation record);

    HairdressingReservation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HairdressingReservation hairdressingReservation);

    int updateByPrimaryKey(HairdressingReservation record);

    Integer selectSunPriceByLoginId(String loginId);

    List<HairdressingReservation> selectAllReservations();

    List<HairdressingReservation> selectReservationsByCode(String code);

    List<HairdressingReservation> selectAllReservationsByDay(String day);
}