package com.ecomm.mapper;

import com.ecomm.Model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        Product product=new Product();

        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setStockQuantity(rs.getInt("stock_quantity"));
        product.setCategory_id(rs.getInt("category_id"));
        product.setVendor_id(rs.getInt("vendor_id"));

        product.setCategoryName(rs.getString("category_name"));
        product.setVendorName(rs.getString("vendor_name"));

        return product;
    }
}
