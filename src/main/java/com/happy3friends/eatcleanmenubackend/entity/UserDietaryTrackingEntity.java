package com.happy3friends.eatcleanmenubackend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "UserDietaryTracking", schema = "dbo", catalog = "ecm")
public class UserDietaryTrackingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "UserGmail", nullable = false, length = 100)
    private String userGmail;
    @Basic
    @Column(name = "Datetime", nullable = false)
    private Date datetime;
    @Basic
    @Column(name = "UserWeight", nullable = true, precision = 0)
    private Double userWeight;
    @Basic
    @Column(name = "UserCalories", nullable = true, precision = 0)
    private Double userCalories;

}