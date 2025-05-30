package com.incetutku.ecom.repository;

import com.incetutku.ecom.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
