package com.happy3friends.eatcleanmenubackend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "UserDietaryInfo", schema = "dbo", catalog = "ECM")
public class UserDietaryInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "UserGmail", nullable = false, length = 100)
    private String userGmail;
    @Basic
    @Column(name = "DietTarget", nullable = false, length = 20)
    private String dietTarget;
    @Basic
    @Column(name = "UserAge", nullable = false)
    private int userAge;
    @Basic
    @Column(name = "UserHeight", nullable = false, precision = 0)
    private double userHeight;
    @Basic
    @Column(name = "UserWeight", nullable = false, precision = 0)
    private double userWeight;
    @Basic
    @Column(name = "ActivityRate", nullable = false, length = 50)
    private String activityRate;
    @Basic
    @Column(name = "BMI", nullable = false, precision = 0)
    private double bmi;
    @Basic
    @Column(name = "BMR", nullable = false, precision = 0)
    private double bmr;
}