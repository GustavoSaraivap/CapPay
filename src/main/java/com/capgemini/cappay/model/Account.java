package com.capgemini.cappay.model;

import com.capgemini.cappay.enums.AccountStatus;
import com.capgemini.cappay.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User userId;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "last_change_date")
    @Temporal(TemporalType.DATE)
    private Date lastChangeDate;

    @Column(length = 35)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(length = 35)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}
