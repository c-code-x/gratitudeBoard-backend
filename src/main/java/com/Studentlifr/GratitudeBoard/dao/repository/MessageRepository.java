package com.Studentlifr.GratitudeBoard.dao.repository;

import com.Studentlifr.GratitudeBoard.dao.MessageDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageDAO, UUID> {

}
