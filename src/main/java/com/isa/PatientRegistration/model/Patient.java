package com.isa.PatientRegistration.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
// Patient information
    private Long id;
    private String name;
    private String email;
    private String phone;
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

//***********************

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // photo

    public byte[] getPhoto() { return photo; }
    public void setPhoto(byte[] photo) { this.photo = photo; }

    public String getPhotoType() { return photoType; }
    public void setPhotoType(String photoType) { this.photoType = photoType; }

}
