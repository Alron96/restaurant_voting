package com.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "vote_date"}, name = "user_unique_vote_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "restaurant_id", updatable = false, insertable = false)
    private Integer restaurant_fk;

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
                ", restaurantId=" + this.getRestaurant().getId() +
                ", userId=" + this.getUser().getId() +
                ", voteDate=" + this.getVoteDate() +
                ")";
    }
}
