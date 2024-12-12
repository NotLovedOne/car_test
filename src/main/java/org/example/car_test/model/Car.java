package org.example.car_test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank
    private String make;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotBlank String getMake() {
        return make;
    }

    public void setMake(@NotBlank String make) {
        this.make = make;
    }

    public @NotBlank String getModel() {
        return model;
    }

    public void setModel(@NotBlank String model) {
        this.model = model;
    }

    @Min(1886)
    public int getYear() {
        return year;
    }

    public void setYear(@Min(1886) int year) {
        this.year = year;
    }

    @Positive
    public double getPrice() {
        return price;
    }

    public void setPrice(@Positive double price) {
        this.price = price;
    }

    public @NotNull @Size(min = 17, max = 17) String getVin() {
        return vin;
    }

    public void setVin(@NotNull @Size(min = 17, max = 17) String vin) {
        this.vin = vin;
    }

    @NotBlank
    private String model;

    @Min(1886)
    private int year;

    @Positive
    private double price;

    @NotNull
    @Size(min = 17, max = 17)
    @Column(unique = true)
    private String vin;

}

