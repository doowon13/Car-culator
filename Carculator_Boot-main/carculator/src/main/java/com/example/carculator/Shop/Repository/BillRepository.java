package com.example.carculator.Shop.Repository;

import com.example.carculator.Shop.Entity.Bill;
import com.example.carculator.Shop.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, String> {
    List<Bill> findByMemberCode(String memberCode);
}
