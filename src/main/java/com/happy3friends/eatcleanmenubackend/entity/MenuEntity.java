package com.happy3friends.eatcleanmenubackend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "Menu", schema = "dbo", catalog = "ECM")
public class MenuEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "UserId", nullable = false)
    private int userId;
    @Basic
    @Column(name = "MenuPeriodStartDate", nullable = true)
    private Date menuPeriodStartDate;
    @Basic
    @Column(name = "MenuPeriodEndDate", nullable = true)
    private Date menuPeriodEndDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", referencedColumnName = "Id", insertable = false, updatable = false)
    private UsersEntity usersByUserId;
    @OneToMany(mappedBy = "menuByMenuId")
    private Collection<MenuDishEntity> menuDishesById;

}
