package com.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"vote_date", "user_id"}, name = "user_unique_vote_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "restaurant_id", updatable = false, insertable = false)
    @NotNull
    private Integer restaurant_fk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonIgnore
    private User user;

    @Column(name = "user_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Integer user_fk;

    @Column(name = "vote_date", nullable = false)
    @NotNull
    private LocalDate voteDate;

    public Vote(Integer id, LocalDate voteDate, Restaurant restaurant, User user) {
        super(id);
        this.voteDate = voteDate;
        this.restaurant = restaurant;
        this.user = user;
    }

    public String toString() {
        return "Vote(" +
                super.toString() +
                ", restaurantId=" + this.getRestaurant().id() +
                ", userId=" + this.getUser().id() +
                ", voteDate=" + this.getVoteDate() +
                ")";
    }
}
