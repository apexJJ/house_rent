package com.example.jpa.service.Impl;

import com.example.jpa.dao.ActivityDao;
import com.example.jpa.dao.ActivitySpecificationDao;
import com.example.jpa.dao.HouseDao;
import com.example.jpa.entity.Activity;
import com.example.jpa.entity.House;
import com.example.jpa.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;


@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    HouseDao houseDao;



    @Override
    public Page<House> findAllData(Integer NowPage, Integer size){
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable  =  PageRequest.of(NowPage,size);
        Page<House> pagedata =  houseDao.findAll(pageable);
        System.out.println(pagedata);
        return pagedata;

    }


    @Override
    public List<House> queryData(String place,Integer price,Integer area,String room,String state){
        List<House> data =  houseDao.queryHouse(place,price,area,room,state);
        System.out.println(data);
        return data;
    }

    @Override
    public List<House> findHouseById(Integer id) {
        List<House> data =  houseDao.findHouseById(id);
        System.out.println(data);
        return data;
    }
}
