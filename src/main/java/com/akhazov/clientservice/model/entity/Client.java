package com.akhazov.clientservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID bankId;

    private String firstName;

    private String middleName;

    private String lastName;

    private LocalDate birthdate;

    private String passportId;

    private String birthplace;

    private String phoneNumber;

    private String email;

    private String registrationAddress;

    private String residentialAddress;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @Version
    private Integer version;

}
