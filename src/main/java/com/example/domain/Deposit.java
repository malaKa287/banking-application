package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "DEPOSIT")
@Data
public class Deposit {

    @Id
    @GeneratedValue
    private String id;

    @Column
    private Integer value;

    @OneToOne(mappedBy = "deposit")
    private User user;
}
