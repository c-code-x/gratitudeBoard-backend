package com.Studentlifr.GratitudeBoard.dao.repository;

import com.Studentlifr.GratitudeBoard.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDAO, UUID> {
    Boolean existsByEmailId(String emailId);

    Optional<UserDAO> findByEmailId(String emailId);
}
