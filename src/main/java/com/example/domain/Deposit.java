package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "DEPOSIT")
@Validated
@Data
public class Deposit {

    @Id
    @GeneratedValue
    @Column
    private String id;

    @Column
    @NotNull
    private Integer value;

    @OneToOne(mappedBy = "deposit")
    private User user;
}
