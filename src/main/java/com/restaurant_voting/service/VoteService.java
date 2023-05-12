package com.restaurant_voting.service;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.repository.UserRepository;
import com.restaurant_voting.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    @Transactional
    public Vote save(Vote vote, int userId) {
        vote.setUser(userRepository.getExisted(userId));
        return voteRepository.save(vote);
    }
}
