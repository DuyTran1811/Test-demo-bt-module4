package com.test.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}

