package com.test.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    private Country country;
    private String area;
    private String population;
    private Long gdp;
    @Lob
    private String description;
}

