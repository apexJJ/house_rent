package com.example.jpa.dao;

import com.example.jpa.entity.Activity;
import com.example.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository
public interface ActivityDao extends JpaRepository<Activity, Integer> {


    //查询所有的活动数据
    @Query(value = "select * from activity ", nativeQuery = true)
    List<Activity> findActivity();

    @Transactional
    @Modifying
    void deleteActivityById(int id);


//    @Query(value = "delete from activity where id=?1",nativeQuery = true)
//    @Modifying
//    void deleteActivityById(Integer id);


    //更新部分字段 修改用户信息
    @Transactional
    @Modifying
    @Query(value = "update activity a set a.name=?1,a.region=?2,a.date=?3 where a.id=?4", nativeQuery = true)
    void update( String name, String region,Date date,  int id);


//    //更新部分字段 修改用户信息
//    @Transactional
//    @Modifying
//    @Query(value = "update activity set name=:name,region=:region,date=:date where id=:id", nativeQuery = true)
//    void updatetwo(Activity activity);

}
