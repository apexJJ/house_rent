package com.example.jpa.service.Impl;

import com.example.jpa.dao.ActivityDao;
import com.example.jpa.dao.ActivitySpecificationDao;
import com.example.jpa.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.*;
import java.util.List;

import static org.springframework.data.domain.PageRequest.of;


@Service
public class AcService {

    @Autowired
    ActivityDao activitydao;

    @Autowired
    ActivitySpecificationDao activitySpecificationDao;

    public void update(Activity activity){
        activitydao.save(activity);
    }


    public Page<Activity> findAllData(int NowPage,int size){
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable  =  PageRequest.of(NowPage,size);
        Page<Activity> activityPage =  activitydao.findAll(pageable);
        System.out.println(activityPage);
        return activityPage;

    }


    public Page<Activity> creatile(){
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable  =  PageRequest.of(0,5,sort);
        /*
        * root：就是我们要查询的类型
        *query：查询条件
        * cb:构建Predicate
        * */
        Specification<Activity> specification = new Specification<Activity>() {
            @Override
            public Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
               //root(activity(age))通过root拿到类里面的属性
                Path path = root.get("id");
                return cb.gt(path,1);
            }
        };
        Page<Activity> activityPage =  activitySpecificationDao.findAll(specification,pageable);
        System.out.println(activityPage);
        return activityPage;

    }
}
