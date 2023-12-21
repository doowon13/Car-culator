package com.example.carculator.Car.Repository;

import com.example.carculator.Car.Entity.MyCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<MyCar, Long>, CarRepositoryCustom {

    void deleteByMembercode(String membercode);
}
