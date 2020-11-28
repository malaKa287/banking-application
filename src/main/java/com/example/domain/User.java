package com.example.domain;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "USER")
@Validated
@Data
public class User {

    @Id
    @GeneratedValue
    @Column
    private UUID id;

    @NotBlank
    @Email
    @Column
    private String email;

    @NotBlank
    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEPOSIT_ID")
    private Deposit deposit;

    @NotBlank
    @Transient
    private String passwordConfirm;

}
