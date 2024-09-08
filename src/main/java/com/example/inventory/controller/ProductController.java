package com.example.inventory.controller;

import java.util.List; // Import the List class

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.inventory.dto.ProductDTO; // Import the new DTO class
import com.example.inventory.entity.Product;
import com.example.inventory.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // Constructor Injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Add Product - Uses ProductDTO
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product createdProduct = productService.addProduct(product);
        return new ResponseEntity<>(convertToDTO(createdProduct), HttpStatus.CREATED);
    }

    // Get Product by ID - Returns ProductDTO
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(convertToDTO(product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get All Products - Returns a List of ProductDTO
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = products.stream()
                .map(productService::convertToDTO) // Ensure this matches the method signature
                .toList();
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    // Update Product Stock - Uses ProductDTO for the updated stock
    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductDTO> updateProductStock(@PathVariable Long id, @RequestParam int newStock) {
        Product updatedProduct = productService.updateProductStock(id, newStock);
        if (updatedProduct != null) {
            return new ResponseEntity<>(convertToDTO(updatedProduct), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Process Order - Custom response message
    @PutMapping("/{id}/order")
    public ResponseEntity<String> processOrder(@PathVariable Long id, @RequestParam int quantity) {
        boolean success = productService.processOrder(id, quantity);
        if (success) {
            return new ResponseEntity<>("Order processed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order failed, insufficient stock or product not found",
                    HttpStatus.BAD_REQUEST);
        }
    }

    // Utility method to convert DTO to entity
    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        return product;
    }

    // Utility method to convert entity to DTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        return productDTO;
    }
}
