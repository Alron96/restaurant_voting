package com.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
public class Dish extends AbstractNamedEntity {

    @Column(name = "dish_date", nullable = false)
    @NotNull
    private LocalDate dishDate;

    @Column(name = "price", nullable = false)
    @NotBlank
    @Size(max = 128)
    private Integer price;
}
