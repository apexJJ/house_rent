package com.example.jpa.dao;

import com.example.jpa.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository
public interface ActivitySpecificationDao extends JpaRepository<Activity, Integer>, JpaSpecificationExecutor<Activity> {


}
