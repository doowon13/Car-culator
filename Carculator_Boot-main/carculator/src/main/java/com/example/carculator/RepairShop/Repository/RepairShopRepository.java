package com.example.carculator.RepairShop.Repository;

import com.example.carculator.RepairShop.Entity.RepairShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepairShopRepository extends JpaRepository<RepairShop, String> {

    @Query("SELECT r FROM RepairShop r WHERE r.repairshop_rate = :repairShopRate")
    List<RepairShop> findByRepairShopRate(@Param("repairShopRate") String repairShopRate);

}
