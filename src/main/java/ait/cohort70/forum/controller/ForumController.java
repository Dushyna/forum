package ait.cohort70.forum.controller;

import ait.cohort70.forum.dto.CommentMessageDto;
import ait.cohort70.forum.dto.PostAuthorDto;
import ait.cohort70.forum.dto.PostDto;
import ait.cohort70.forum.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ForumController {
    private final ForumService forumService;

    @PostMapping("/forum/post/{user}")
    public PostAuthorDto addPost(@PathVariable String user, @RequestBody PostDto postDto) {
        return forumService.addPost(user, postDto);
    }

    @GetMapping("/forum/post/{postId}")
    public PostAuthorDto findPostById(@PathVariable Long postId) {
        return forumService.findPostById(postId);
    }

    @PatchMapping("/forum/post/{postId}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(@PathVariable Long postId) {
        forumService.addLike(postId);
    }

    @GetMapping("/forum/posts/author/{user}")
    public List<PostAuthorDto> findPostsByAuthor(@PathVariable String user) {
        return forumService.findPostsByAuthor(user);
    }

    @PatchMapping("/forum/post/{postId}/comment/{commenter}")
    public PostAuthorDto addComment(@PathVariable Long postId, @PathVariable String commenter, @RequestBody CommentMessageDto commentMessageDto) {
        return forumService.addComment(postId, commenter, commentMessageDto);
    }

    @DeleteMapping("/forum/{postId}")
    public PostAuthorDto deletePost(@PathVariable Long postId) {
        return forumService.deletePost(postId);
    }

    @GetMapping("/forum/posts/tags")
    public List<PostAuthorDto> findPostsByTags(@RequestParam String values) {
        return forumService.findPostsByTags(values);
    }

    @GetMapping("/forum/posts/period")
    public List<PostAuthorDto> findPostsByPeriod(@RequestParam Date dateFrom, @RequestParam Date dateTo) {
        return forumService.findPostsByPeriod(dateFrom, dateTo);
    }

    @PatchMapping("/forum/post/{postId}")
    public PostAuthorDto updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        return forumService.updatePost(postId, postDto);
    }


}
