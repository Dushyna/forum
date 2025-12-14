package ait.cohort70.forum.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostDto {
    private String title;
    private String content;
    private List<String> tags;
}
