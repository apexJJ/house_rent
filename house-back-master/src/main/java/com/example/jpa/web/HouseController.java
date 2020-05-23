package com.example.jpa.web;

import com.example.jpa.base.ApiResponse;
import com.example.jpa.entity.House;
import com.example.jpa.entity.User;
import com.example.jpa.service.HouseService;
import com.example.jpa.service.Impl.HouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping(value = "/getAllData", method = RequestMethod.GET)
    public ApiResponse getallData(Integer NowPage, Integer size) {
        Page<House> list = houseService.findAllData(NowPage,size);
        if (list.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(list);
    }

    @RequestMapping(value = "/queryData", method = RequestMethod.GET)
    public ApiResponse queryData(String place,Integer price,Integer area,String room,String state) {
        List<House> list = houseService.queryData(place,price,area,room,state);
        if (list.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(list);
    }

    @RequestMapping(value = "/houseDetail", method = RequestMethod.GET)
    public ApiResponse getDetail(Integer id) {
        List<House> list = houseService.findHouseById(id);
        if (list.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        }
        return ApiResponse.ofSuccess(list);
    }
}

