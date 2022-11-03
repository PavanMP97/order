package com.intenship.order.repository;

import com.intenship.order.entity.OrderDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDomain, Long> {
    @Query(value = "select * from order_table where user_id=?1", nativeQuery = true)
    List<OrderDomain> findAllByUserId(Long user_id);

}
