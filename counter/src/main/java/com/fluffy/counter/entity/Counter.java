package com.fluffy.counter.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Counter {

    @Id
    private String name;

    @Column(nullable = false)
    private int value;

}
