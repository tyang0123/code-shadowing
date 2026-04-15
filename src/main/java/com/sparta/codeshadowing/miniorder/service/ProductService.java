package com.sparta.codeshadowing.miniorder.service;

import com.sparta.codeshadowing.miniorder.dto.ProductRequestDto;
import com.sparta.codeshadowing.miniorder.dto.ProductResponseDto;
import com.sparta.codeshadowing.miniorder.entity.Product;
import com.sparta.codeshadowing.miniorder.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
//sunny - readOnly 걸어놔서 create, update, delete 에는 Transactional 어노테이션이 있는건강 rollback 환경 땜에??
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto createProduct(@Valid ProductRequestDto request) {
        Product product = new Product(request.getName(), request.getPrice());
        Product saved = productRepository.save(product);
        return new ProductResponseDto(saved);
    }

    public ProductResponseDto getProduct(Long id) {
        Product product = findProductById(id);
        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> getProductList() {
        return productRepository.findAll().stream().map(ProductResponseDto::new).toList();
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, @Valid ProductRequestDto request) {
        Product product = findProductById(id);
        product.update(request.getName(), request.getPrice());
        return new ProductResponseDto(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. id=" + id));
    }
}
