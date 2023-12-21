package com.example.carculator.Car.Repository;

import com.example.carculator.Car.Entity.MyCarDto;

import java.util.List;
import java.util.Optional;

public interface CarRepositoryCustom {

    // 내 차량 조회
    Optional<MyCarDto> findMyCarByMembercode(String membercode);

    // 내 차 등록 전 차량 수 확인
    int countMyCarByMembercode(String membercode);
}
