package com.example.carculator.Shop.Repository;

import com.example.carculator.Shop.Entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    List<BillDetail> findByBillNo(String billNo);
}
