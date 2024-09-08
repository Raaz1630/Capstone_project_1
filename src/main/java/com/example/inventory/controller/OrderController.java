package com.example.inventory.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.inventory.entity.Order;
import com.example.inventory.dto.OrderDTO;  // Assuming you have a new DTO package
import com.example.inventory.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // Constructor Injection
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create Order - Accepts an OrderDTO object
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderService.createOrder(order);
        return ResponseEntity.ok(convertToDTO(savedOrder));
    }

    // Get Order by ID - Returns an OrderDTO object
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        Optional<Order> orderOpt = orderService.getOrder(id);
        if (orderOpt.isPresent()) {
            return ResponseEntity.ok(convertToDTO(orderOpt.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update Order - Uses OrderDTO for input and output
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order updatedOrder = orderService.updateOrder(id, order);
        return ResponseEntity.ok(convertToDTO(updatedOrder));
    }

    // Delete Order - Keeps as is
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    // Utility method to convert DTO to entity
    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setProductName(orderDTO.getProductName());
        order.setQuantity(orderDTO.getQuantity());
        order.setCustomerName(orderDTO.getCustomerName());
        return order;
    }

    // Utility method to convert entity to DTO
    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setProductName(order.getProductName());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setCustomerName(order.getCustomerName());
        return orderDTO;
    }
}
