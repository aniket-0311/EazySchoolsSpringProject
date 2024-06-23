package com.eazybytes.demo.repository;

import com.eazybytes.demo.model.Contact;
import com.eazybytes.demo.rommappers.ContactRowMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {

    List<Contact> findByStatus(String status);

    @Query("SELECT c FROM Contact c WHERE c.status = :status")
//    @Query(value = "Select * from contact_msg c where c.status = :status",nativeQuery = true)
    Page<Contact> findByStatusWithQuery(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("Update Contact c set c.status = ?1 where c.contactId = ?2")
    int updateStatusById(String status, int contactId);
}
