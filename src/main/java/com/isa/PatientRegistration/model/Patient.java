package com.isa.PatientRegistration.model;

//import statements

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;



//  entity definition and lombok annotations

@Entity
@Getter
@Setter



public class Patient {

    //primary key configuration

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


// Patient information

    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;

    //photo storage


    @Lob
    private byte[] photo;
    private String photoType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
