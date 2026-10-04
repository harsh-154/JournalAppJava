package com.edigest.journalApp.repository;

//import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, String> {
    Users findByUsername(String username);
    Users deleteByUsername(String username);
}

//controller ---calls-->service----calls---> repository