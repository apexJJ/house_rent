package com.example.jpa.web;

import com.alibaba.fastjson.JSONObject;
import com.example.jpa.base.ApiResponse;
import com.example.jpa.dao.ProductDao;
import com.example.jpa.entity.House;
import com.example.jpa.entity.Product;
import com.example.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import javax.swing.text.Caret;
import java.util.*;

@RestController
@RequestMapping("/shop")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ApiResponse getallData(String category) {
        List<Product> list = productService.findByCategory(category);
        if (list.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(list);
    }

    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
    public ApiResponse queryData(Integer id) {
        List<Product> list = productService.findById(id);
        if (list.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(list);
    }

    @RequestMapping(value = "/categoryData", method = RequestMethod.GET)
    public List<JSONObject> queryDatas() {
            return productService.findData();
    }

    @RequestMapping(value = "/AllData", method = RequestMethod.GET)
    public List<Product> getAllDatas() {
        return productService.findAll();
    }
}
