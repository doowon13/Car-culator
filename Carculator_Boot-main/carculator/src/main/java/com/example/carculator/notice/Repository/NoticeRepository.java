package com.example.carculator.notice.Repository;

import com.example.carculator.notice.Entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {


    @Query(value = "select max(notice_no) from notice", nativeQuery = true)
    long findLast();


}
