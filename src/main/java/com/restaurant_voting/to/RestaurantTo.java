package com.restaurant_voting.to;

import com.restaurant_voting.HasId;
import com.restaurant_voting.model.Dish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantTo implements HasId, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank
    @Size(max = 128)
    private String name;

    private List<Dish> dishes;

    private int votes;

    @Override
    public String toString() {
        return "RestaurantTo(" +
                "id=" + this.getId() +
                ", name=" + this.getName() +
                ", dishes=" + this.getDishes() +
                ", votes=" + this.getVotes() +
                ")";
    }
}