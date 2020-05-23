package com.example.jpa.dao;

import com.example.jpa.entity.Activity;
import com.example.jpa.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository
public interface HouseDao extends JpaRepository<House, Integer> {


    //查询所有的房屋数据
    @Query(value = "select * from house_data ", nativeQuery = true)
    List<House> findHouse();

// 根据城市关键词查询房屋数据
    @Query(value = "select * from house_data as u where u.place like CONCAT('%',?1,'%') or u.price=?2 or u.area=?3 or u.room=?4 or u.state=?5",nativeQuery = true)
    List<House> queryHouse(String place,Integer price,Integer area,String room,String state);

    List<House> findHouseById(Integer id);

}
