package com.example.jpa.dao;

import com.example.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    //根据商品类别查询所有商品
    List<Product> findProductByCategory(String category);

    //根据id查询信息
    @Query(value = "select * from product where id = ?1", nativeQuery = true)
    List<Product> getProductById(Integer id);

    List<Product> findAll();
    //更新
    @Transactional
    @Modifying
    @Query(value = "update product_info set product_name=:productName,product_num=:productNum,product_order=:productOrder,product_price=:productPrice,product_sale=:productSale,product_status=:productStatus where product_id=:productId", nativeQuery = true)
    void modifyProduct(@Param("productId") Integer productId, @Param("productName") String productName, @Param("productNum") Integer productNum, @Param("productSale") Integer productSale, @Param("productPrice") Double productPrice, @Param("productStatus") String productStatus, @Param("productOrder") String productOrder);

}
