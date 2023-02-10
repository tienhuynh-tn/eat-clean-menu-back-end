package com.happy3friends.eatcleanmenubackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "Tip", schema = "dbo", catalog = "ECM")
public class TipEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "Title", nullable = false, length = 200)
    private String title;
    @Basic
    @Column(name = "Content", nullable = false, length = -1)
    private String content;
    @Basic
    @Column(name = "Image", nullable = false, length = -1)
    private String image;
    @Basic
    @Column(name = "CreatedDate", nullable = false)
    private Date createdDate;
}