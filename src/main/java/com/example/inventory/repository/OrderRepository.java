package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventory.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
