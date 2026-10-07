package com.ecomm.Service;

import com.ecomm.Model.Product;
import com.ecomm.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void save(Product product){
        productRepository.insert(product);
    }

    public List<Product> findById(int id) {

        return productRepository.findById(id);
    }

    public void updateStock(int id, int updatedStock) {
        productRepository.updateStock(id,updatedStock);
    }

    public Map<String, Integer> countProductsByVendor() {
        return productRepository.countProductsByVendor();
    }
}
