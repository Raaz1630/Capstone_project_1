package com.example.inventory.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.inventory.entity.Order;
import com.example.inventory.service.OrderService;
import com.example.inventory.dto.OrderDTO; // Correct package for OrderDTO

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // Constructor Injection
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create Order
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderService.createOrder(order);
        return ResponseEntity.ok(convertToDTO(savedOrder));
    }

    // Get Order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        Optional<Order> orderOpt = orderService.getOrder(id);
        if (orderOpt.isPresent()) {
            return ResponseEntity.ok(convertToDTO(orderOpt.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update Order
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order updatedOrder = orderService.updateOrder(id, order);
        return ResponseEntity.ok(convertToDTO(updatedOrder));
    }

    // Delete Order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    // Convert DTO to Entity
    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setProductId(orderDTO.getProductId());
        order.setQuantity(orderDTO.getQuantity());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setCustomerName(orderDTO.getCustomerName()); // Ensure this method exists in Order
        return order;
    }

    // Convert Entity to DTO
    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setProductId(order.getProductId());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setCustomerName(order.getCustomerName()); // Ensure this method exists in Order
        return orderDTO;
    }
}
