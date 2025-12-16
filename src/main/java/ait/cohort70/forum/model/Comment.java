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
    @Column(name="id")
    private long id;
    @Column(name="username")
    private String userName;
    @Column(name="message", columnDefinition = "TEXT")
    private String message;
    @Column(name="date_created")
    private LocalDateTime dateCreated = LocalDateTime.now();
    @Column(name="likes")
    private int likes;
    @ManyToOne
    @JoinColumn(name = "post_id")
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
