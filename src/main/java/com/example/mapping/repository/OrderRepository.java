package com.example.mapping.repository;

import com.example.mapping.one_to_one_unidirectional.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findByOrderTrackingNumber(String orderTrackingNumber);

}
