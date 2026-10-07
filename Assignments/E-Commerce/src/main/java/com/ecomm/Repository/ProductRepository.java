package com.ecomm.Repository;

import com.ecomm.Model.Product;
import com.ecomm.mapper.ProductMapper;
import com.ecomm.mapper.VendorProductCountMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProductMapper productMapper;
    private final VendorProductCountMapper vendorProductCountMapper;

    public ProductRepository(JdbcTemplate jdbcTemplate, ProductMapper productMapper, VendorProductCountMapper vendorProductCountMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.productMapper = productMapper;
        this.vendorProductCountMapper = vendorProductCountMapper;
    }

    public void  insert(Product product) {
        String sql="insert into product values(?,?,?,?,?,?)";
        Object[] values=new Object[]{product.getId(),product.getName(),product.getPrice(),product.getStockQuantity(),product.getCategory_id(),product.getVendor_id()};
        jdbcTemplate.update(sql,values);
    }

    public List<Product> findById(int id) {

        String sql = """
                SELECT p.id,
                       p.name,
                       p.price,
                       p.stock_quantity,
                       p.category_id,
                       p.vendor_id,
                       c.name AS category_name,
                       v.name AS vendor_name
                FROM product p
                JOIN category c ON p.category_id = c.id
                JOIN vendor v ON p.vendor_id = v.id
                WHERE p.id = ?
                """;
        return jdbcTemplate.query(sql,productMapper,id);
    }

    public void updateStock(int id, int updatedStock) {
        String sql = "UPDATE product SET stock_quantity = ? WHERE id = ?";

        jdbcTemplate.update(sql, updatedStock, id);
    }

    public Map<String, Integer> countProductsByVendor() {
        String sql="""
            SELECT v.name AS vendor_name,
                   COUNT(p.id) AS product_count
            FROM product p
            JOIN vendor v ON p.vendor_id = v.id
            GROUP BY v.id, v.name
            """;

        return jdbcTemplate.query(sql,vendorProductCountMapper)
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));

    }
}
