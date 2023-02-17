package com.happy3friends.eatcleanmenubackend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserDietaryTracking", schema = "dbo", catalog = "ecm")
public class UserDietaryTrackingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "Datetime", nullable = false)
    private Timestamp datetime;
    @Basic
    @Column(name = "UserWeight", nullable = true, precision = 0)
    private Double userWeight;
    @Basic
    @Column(name = "UserCalories", nullable = true, precision = 0)
    private Double userCalories;
    @Basic
    @Column(name = "UserId", nullable = false)
    private int userId;

}
