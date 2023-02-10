package com.happy3friends.eatcleanmenubackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "Menu", schema = "dbo", catalog = "ECM")
public class MenuEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "UserGmail", nullable = false, length = 100)
    private String userGmail;
    @Basic
    @Column(name = "MenuPeriodStartDate", nullable = true)
    private Date menuPeriodStartDate;
    @Basic
    @Column(name = "MenuPeriodEndDate", nullable = true)
    private Date menuPeriodEndDate;
}