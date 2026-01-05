package ait.cohort70.forum.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
@Column(name="id")
    private long id;
    @Setter
    @Column(name="title")
    private String title;
    @Setter
    @Column(name="content", columnDefinition = "TEXT")
    private String content;
    @Setter
    @Column(name="author")
    private String author;
    @Column(name="date_created")
    private LocalDateTime  dateCreated= LocalDateTime.now();
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="posts_tags",
            joinColumns= @JoinColumn(name="post_id"),
            inverseJoinColumns=  @JoinColumn(name = "tag_name")
    )

    private Set<Tag> tags = new HashSet<>();
    @Column(name="likes")
    private int likes;
   // @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<AttachedFile> files = new ArrayList<>();

    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

        public Post(String title, String content, String author, Set<String> tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags.addAll(tags.stream().map(Tag::new).toList());
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    //public void addFile(AttachedFile attachedFile){ files.add(attachedFile);}

    public void addLike(){
        likes++;
    }

    public boolean addTag(Tag tag){
       return tags.add(tag);


    }

}
