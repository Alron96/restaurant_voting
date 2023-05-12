package com.restaurant_voting.to;

import com.restaurant_voting.HasId;
import com.restaurant_voting.model.Dish;
import com.restaurant_voting.model.Vote;
import com.restaurant_voting.util.validation.NoHtml;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantTo implements HasId {

    private Integer id;

    @NotBlank
    @Size(max = 128)
    @NoHtml
    private String name;

    private List<Dish> dishes;

    private int votes;

    private Vote authUserVote;

    @Override
    public String toString() {
        return "RestaurantTo(" +
                "id=" + this.id() +
                ", name=" + this.getName() +
                ", dishes=" + this.getDishes() +
                ", votes=" + this.getVotes() +
                ")";
    }
}