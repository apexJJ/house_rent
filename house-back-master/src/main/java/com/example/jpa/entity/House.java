package com.example.jpa.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "house_data")
public class House implements Serializable {
    private static final long serialVersionUID = 5787774890977985998L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column
    private String name;
    @Column
    private String place;
    @Column
    private Integer price;
    @Column
    private Integer area;
    @Column
    private String room;
    @Column
    private String state;
    @Column
    private String image;
    @Column
    private String direction;
    @Column
    private String subway;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date createTime;

}
