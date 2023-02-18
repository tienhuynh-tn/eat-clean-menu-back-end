package com.happy3friends.eatcleanmenubackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipDTO {
    private int id;
    private String title;
    private String content;
    private String image;
    private Date createdDate;
}
