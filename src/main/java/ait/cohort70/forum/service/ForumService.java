package ait.cohort70.forum.service;

import ait.cohort70.forum.dto.CommentDto;
import ait.cohort70.forum.dto.CommentMessageDto;
import ait.cohort70.forum.dto.PostAuthorDto;
import ait.cohort70.forum.dto.PostDto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ForumService {
    PostAuthorDto addPost(String user, PostDto postDto);

    PostAuthorDto findPostById(Long id);

    void addLike(Long id);

    PostAuthorDto findPostByAuthor(String user);

    PostAuthorDto addComment(Long id, String commenter, CommentMessageDto commentMessageDto);

    PostAuthorDto deletePost(Long id);

    List<PostAuthorDto> findPostByTags(String tags);

    List<PostAuthorDto> findPostByPeriod(Date dateFrom, Date dateTo);

    PostAuthorDto updatePost(Long id, PostDto postDto);

}
