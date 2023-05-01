package com.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends AbstractNamedEntity {

    @Column(name = "dish_date", nullable = false)
    @NotNull
    private LocalDate dishDate;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 10, max = 50000)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Dish(Integer id, String name, Integer price, LocalDate dishDate, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.dishDate = dishDate;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        String id_name = super.toString();
        return "Dish(" +
                id_name +
                ", dishDate=" + this.getDishDate() +
                ", price=" + this.getPrice() +
                ")";
    }
}
