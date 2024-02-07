package com.coderscampus.coursereportapiintegration.repository;

import com.coderscampus.coursereportapiintegration.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    // select * from match_contact where email = 'blahblah'
    Optional<Match> findByEmail(String email);
}
