package com.example.jpa.service;

import com.alibaba.fastjson.JSONObject;
import com.example.jpa.entity.House;
import com.example.jpa.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {


    List<Product> findByCategory(String category);

    List<Product> findById(Integer id);

    List<JSONObject> findData();

    List<Product> findAll();
}
