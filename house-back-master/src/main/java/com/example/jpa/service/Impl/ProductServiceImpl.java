package com.example.jpa.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.jpa.dao.HouseDao;
import com.example.jpa.dao.ProductDao;
import com.example.jpa.entity.House;
import com.example.jpa.entity.Product;
import com.example.jpa.service.HouseService;
import com.example.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;


    @Override
    public List<Product> findByCategory(String category) {
        return productDao.findProductByCategory(category);
    }

    @Override
    public List<Product> findById(Integer id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<JSONObject> findData() {
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        List<Product> list1 = productDao.findProductByCategory("fruit");
        List<Product> list2 = productDao.findProductByCategory("electric");
        List<Product> list3 = productDao.findProductByCategory("food");
        JSONObject object = new JSONObject();
        object.put("title","水果");
        object.put("list",list1);
        JSONObject object1 = new JSONObject();
        object1.put("title","家电");
        object1.put("list",list2);
        JSONObject object2 = new JSONObject();
        object2.put("title","零食");
        object2.put("list",list3);

        jsonObjects.add(object);
        jsonObjects.add(object1);
        jsonObjects.add(object2);
        return jsonObjects;
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

}
