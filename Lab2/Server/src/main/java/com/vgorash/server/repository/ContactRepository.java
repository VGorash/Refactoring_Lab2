package com.vgorash.server.repository;

import com.vgorash.server.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    @Query("select c from Contact c where upper(c.name) like upper(?1) and c.userId = ?2")
    List<Contact> getByName(String query, String username);

    @Query("select c from Contact c where upper(c.surname) like upper(?1) and c.userId = ?2")
    List<Contact> getBySurname(String query, String username);

    @Query("select c from Contact c where (upper(c.name) like upper(?1) or upper(c.surname) like upper(?1)) and c.userId = ?2")
    List<Contact> getByFullName(String query, String username);

    @Query("select c from Contact c where upper(c.phone) like upper(?1) and c.userId = ?2")
    List<Contact> getByPhone(String query, String username);

    @Query("select c from Contact c where upper(c.email) like upper(?1) and c.userId = ?2")
    List<Contact> getByEmail(String str, String username);

    @Query("select c from Contact c where (upper(c.name) like upper(?1) " +
            "or upper(c.surname) like upper(?1)" +
            "or upper(c.phone) like upper(?1)" +
            "or upper(c.email) like upper(?1)) and c.userId = ?2")
    List<Contact> getBySubstring(String query, String username);

    List<Contact> findAllByUserId(String username);
}