package com.edigest.journalApp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journalEntries")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode
//use @DATA for all these
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private String id;

    private String title;

    private String content;

    private LocalDateTime time;







//We will use LOMBOK for getter and setters
    //    public LocalDateTime getTime() {
//        return time;
//    }
//
//    public void setTime(LocalDateTime time) {
//        this.time = time;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }


}
