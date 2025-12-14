package ait.cohort70.forum.dto;

import java.util.Date;
import java.util.List;

public class PostAuthorDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Date dateCreated;
    private List<String> tags;
    private int likes;
    private List<CommentDto> comments;
}
