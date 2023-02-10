package com.happy3friends.eatcleanmenubackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "User", schema = "dbo", catalog = "ECM")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Gmail", nullable = false, length = 100)
    private String gmail;
    @Basic
    @Column(name = "Fullname", nullable = false, length = 100)
    private String fullname;
    @Basic
    @Column(name = "Gender", nullable = true)
    private Boolean gender;
    @Basic
    @Column(name = "DateOfBirth", nullable = true)
    private Date dateOfBirth;
    @Basic
    @Column(name = "Phone", nullable = true, length = 10)
    private String phone;
    @Basic
    @Column(name = "Address", nullable = true, length = 200)
    private String address;
    @Basic
    @Column(name = "District", nullable = true, length = 50)
    private String district;
    @Basic
    @Column(name = "Province", nullable = true, length = 50)
    private String province;
    @Basic
    @Column(name = "Avatar", nullable = true, length = -1)
    private String avatar;
    @Basic
    @Column(name = "SubscriptionStatus", nullable = true, length = 20)
    private String subscriptionStatus;
    @Basic
    @Column(name = "SubscriptionDate", nullable = true)
    private Date subscriptionDate;
}