package com.example.jpa.dao;

import com.example.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    //查询 userName userPassword
    //nativeQuery = true作用是使用原生sql语句
    @Query(value = "select * from user_info where userName = ?1 and userPassword = ?2",nativeQuery = true)
    User findByNameAndPassword(String userName, String userPassword);

    //根据 userId 查询信息
    @Query(value = "select * from user_info where user_id = ?1", nativeQuery = true)
    List<User> getUserById(Integer userId);

    //查询 userId
    @Query(value = "select user_id from user_info where user_name = ?1", nativeQuery = true)
    Object getUserId(String userName);

    //更新全部字段
    @Transactional
    @Modifying
    @Query(value = "update user_info set user_id=:userId,user_name=:userName,user_password=:userPassword,user_amount=:userAmount,user_phone=:userPhone,user_home=:userHome,user_company=:userCompany", nativeQuery = true)
    void modifyUserAll(@Param("userId") Integer userId, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userAmount") Integer userAmount, @Param("userPhone") String userPhone, @Param("userHome") String userHome, @Param("userCompany") String userCompany);

    //更新部分字段 修改用户信息
    @Transactional
    @Modifying
    @Query(value = "update user_info set user_phone=:userPhone,user_home=:userHome,user_company=:userCompany where user_id=:userId", nativeQuery = true)
    void modifyUserPart(@Param("userPhone") String userPhone, @Param("userHome") String userHome, @Param("userCompany") String userCompany, @Param("userId") Integer userId);

    //充值
    @Transactional
    @Modifying
    @Query(value = "update user_info set user_amount=:userAmount where user_id=:userId", nativeQuery = true)
    void modifyUserAmount(@Param("userId") Integer userId, @Param("userAmount") Integer userAmount);
}
