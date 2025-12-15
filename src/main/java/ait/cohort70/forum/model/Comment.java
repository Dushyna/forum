package ait.cohort70.forum.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String message;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private int likes;
    @ManyToOne
    @Setter
    private Post post;

    public Comment(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public void addLike(){
        likes++;
    }


}
