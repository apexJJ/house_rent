package com.example.jpa.service;

import com.example.jpa.entity.House;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HouseService {

    Page<House> findAllData(Integer NowPage, Integer size);

    List<House> queryData(String place,Integer price,Integer area,String room,String state);

    List<House> findHouseById(Integer id);
}
