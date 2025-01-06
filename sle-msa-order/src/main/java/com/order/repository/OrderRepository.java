package com.order.repository;

import com.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o left join fetch o.orderItems")
    Page<Order> findAllWithItems(Pageable pageable);
}
