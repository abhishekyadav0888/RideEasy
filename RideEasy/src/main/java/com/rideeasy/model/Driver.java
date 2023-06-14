package com.rideeasy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends AbstractUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;

    @NotNull(message = "Licence can not be null")
    @NotBlank(message = "Mobile Number can not be Blank")
    @Pattern(regexp = "^[A-Z]{2}-[0-9]{13}$", message = "Dummy License Number: AB-1234567890123 Invalid driving license number. Please enter a valid Delhi driving license number.")
    private String licenceNumber;

    private Float rating;
    private Boolean isAvailable=true;

    //RelationShip Start

    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
    private Cab cab;

    @JsonIgnore
    @OneToMany(mappedBy = "driver")
    private List<TripBooking> tripBookings = new ArrayList<>();


}
