package com.happy3friends.eatcleanmenubackend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "Menu", schema = "dbo", catalog = "ecm")
public class MenuEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "MenuPeriodStartDate", nullable = true)
    private Date menuPeriodStartDate;
    @Basic
    @Column(name = "MenuPeriodEndDate", nullable = true)
    private Date menuPeriodEndDate;
    @Basic
    @Column(name = "UserId", nullable = false)
    private int userId;

}
