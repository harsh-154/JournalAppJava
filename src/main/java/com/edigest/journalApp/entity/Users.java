package com.edigest.journalApp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class Users {
    @Id
    private Object id;

    @Indexed(unique = true) //to create index u will have to add this in application properties too
    @NonNull //lombok annotations
    private String username;
    @NonNull
    private String password;

    @DBRef //it will keep reference of journal entries otherwise it wont be possible
    private List<JournalEntry> journalEntries=new ArrayList<>();
    private List<String> roles;
}
