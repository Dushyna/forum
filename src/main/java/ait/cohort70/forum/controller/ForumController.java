package ait.cohort70.forum.controller;

import ait.cohort70.forum.dto.CommentDto;
import ait.cohort70.forum.dto.CommentMessageDto;
import ait.cohort70.forum.dto.PostAuthorDto;
import ait.cohort70.forum.dto.PostDto;
import ait.cohort70.forum.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ForumController {
    private final ForumService forumService;

    @PostMapping("/post/{user}")
    public PostAuthorDto addPost(@PathVariable String user, @RequestBody PostDto postDto) {
        return forumService.addPost(user, postDto);
    }

    @GetMapping("/post/{postId}")
    public PostAuthorDto findPostById(@PathVariable Long postId) {
        return forumService.findPostById(postId);
    }

    @PatchMapping("/post/{postId}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(@PathVariable Long postId) {
        forumService.addLike(postId);
    }

    @GetMapping("/posts/author/{user}")
    public PostAuthorDto findPostByAuthor(@PathVariable String user) {
        return forumService.findPostByAuthor(user);
    }

    @PatchMapping("/post/{postId}/comment/{commenter}")
    public PostAuthorDto addComment(@PathVariable Long postId, @PathVariable String commenter, @RequestBody CommentMessageDto commentMessageDto) {
        return forumService.addComment(postId, commenter, commentMessageDto);
    }

    @DeleteMapping("/{postId}")
    public PostAuthorDto deletePost(@PathVariable Long postId) {
        return forumService.deletePost(postId);
    }

    @GetMapping("/posts/tags")
    public List<PostAuthorDto> findPostByTags(@RequestParam String values) {
        return forumService.findPostByTags(values);
    }

    @GetMapping("/posts/period")
    public List<PostAuthorDto> findPostByPeriod(@RequestParam Date dateFrom, @RequestParam Date dateTo) {
        return forumService.findPostByPeriod(dateFrom, dateTo);
    }

    @PatchMapping("/post/{postId}")
    public PostAuthorDto updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        return forumService.updatePost(postId, postDto);
    }


}
