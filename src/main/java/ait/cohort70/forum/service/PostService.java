package ait.cohort70.forum.service;

import ait.cohort70.forum.dto.FileDto;
import ait.cohort70.forum.dto.NewCommentDto;
import ait.cohort70.forum.dto.PostDto;
import ait.cohort70.forum.dto.NewPostDto;
import ait.cohort70.forum.model.AttachedFile;
import ait.cohort70.forum.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface PostService {
    PostDto addNewPost(String author, NewPostDto newPostDto);

    PostDto findPostById(Long id);

    void addLike(Long id);

    PostDto updatePost(Long id, NewPostDto newPostDto);

    PostDto deletePost(Long id);

    PostDto addComment(Long id, String author, NewCommentDto newCommentDto);

    Iterable<PostDto> findPostsByAuthor(String author);

    Iterable<PostDto> findPostsByTags(List<String> tags);

    Iterable<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo);

    void addFileToPost(Long id, MultipartFile file) throws IOException;

    Iterable<FileDto> getFileFromPost(Long id);
}
