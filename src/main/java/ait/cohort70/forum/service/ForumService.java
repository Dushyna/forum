package ait.cohort70.forum.service;

import ait.cohort70.forum.dto.CommentMessageDto;
import ait.cohort70.forum.dto.PostAuthorDto;
import ait.cohort70.forum.dto.PostDto;

import java.util.Date;
import java.util.List;

public interface ForumService {
    PostAuthorDto addPost(String user, PostDto postDto);

    PostAuthorDto findPostById(Long id);

    void addLike(Long id);

    List<PostAuthorDto> findPostsByAuthor(String user);

    PostAuthorDto addComment(Long id, String commenter, CommentMessageDto commentMessageDto);

    PostAuthorDto deletePost(Long id);

    List<PostAuthorDto> findPostsByTags(String tags);

    List<PostAuthorDto> findPostsByPeriod(Date dateFrom, Date dateTo);

    PostAuthorDto updatePost(Long id, PostDto postDto);

}
