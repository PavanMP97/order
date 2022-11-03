package com.intenship.order.repository;

import com.intenship.order.entity.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserDomain, Long> {
    @Modifying
    @Transactional
    @Query(value = "update user_table set money=?1 where user_id=?2", nativeQuery = true)
    void updateMoneyById(int balance, Long userId);

    @Modifying
    @Transactional
    @Query(value = "update user_table set total_orders=?1 where user_id=?2", nativeQuery = true)
    void updateTotalOrdersById(int totalOrder, Long userId);
}
