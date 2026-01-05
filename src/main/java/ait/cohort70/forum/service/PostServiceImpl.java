package ait.cohort70.forum.service;

import ait.cohort70.forum.dao.CommentRepository;
import ait.cohort70.forum.dao.FileRepository;
import ait.cohort70.forum.dao.PostRepositoty;
import ait.cohort70.forum.dao.TagRepository;
import ait.cohort70.forum.dto.FileDto;
import ait.cohort70.forum.dto.NewCommentDto;
import ait.cohort70.forum.dto.NewPostDto;
import ait.cohort70.forum.dto.PostDto;
import ait.cohort70.forum.dto.exceptions.PostNotFoundException;
import ait.cohort70.forum.model.AttachedFile;
import ait.cohort70.forum.model.Comment;
import ait.cohort70.forum.model.Post;
import ait.cohort70.forum.model.Tag;
import ait.cohort70.forum.service.loging.PostLogger;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@Slf4j(topic = "Post Service")
public class PostServiceImpl implements PostService {
    private final PostRepositoty postRepositoty;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PostDto addNewPost(String author, NewPostDto newPostDto) {
        Post post = new Post(newPostDto.getTitle(), newPostDto.getContent(), author);
        //Handle tags
        Set<String> tags = newPostDto.getTags();
        if (tags != null) {
            for (String tagName : tags) {
                Tag tag = tagRepository.findById(tagName).orElseGet(() -> tagRepository.save(new Tag(tagName)));
                post.addTag(tag);
            }
        }
        post = postRepositoty.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto findPostById(Long id) {
        Post post = postRepositoty.findById(id).orElseThrow(PostNotFoundException::new);
        // log.info("Retrieved post with ID {}",id);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    @Transactional
    @PostLogger
    public void addLike(Long id) {
        Post post = postRepositoty.findById(id).orElseThrow(PostNotFoundException::new);
        post.addLike();
        // postRepositoty.save(post);

    }

    @Override
    @Transactional
    @PostLogger
    public PostDto updatePost(Long id, NewPostDto newPostDto) {
        Post post = postRepositoty.findById(id).orElseThrow(PostNotFoundException::new);
        if (newPostDto.getTitle() != null) post.setTitle(newPostDto.getTitle());
        if (newPostDto.getContent() != null) post.setContent(newPostDto.getContent());
        if (newPostDto.getTags() != null) {
            for (String tagName : newPostDto.getTags()) {
                Tag tag = tagRepository.findById(tagName).orElseGet(() -> tagRepository.save(new Tag(tagName)));
                post.addTag(tag);
            }

        }
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    @Transactional
    public PostDto deletePost(Long id) {
        Post post = postRepositoty.findById(id).orElseThrow(PostNotFoundException::new);
        // commentRepository.deleteAll(post.getComments());
        PostDto postDto = modelMapper.map(post, PostDto.class);

        postRepositoty.delete(post);
        //  return modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    @Transactional
    public PostDto addComment(Long id, String author, NewCommentDto newCommentDto) {
        Comment comment = new Comment(author, newCommentDto.getMessage());
        Post post = postRepositoty.findById(id).orElseThrow(PostNotFoundException::new);
        comment.setPost(post);
        // post.addComment(comment);  // Можно не делать
        commentRepository.save(comment);

        return modelMapper.map(post, PostDto.class);
    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<PostDto> findPostsByAuthor(String author) {
        return postRepositoty.findByAuthorIgnoreCase(author)
                .map(x -> modelMapper.map(x, PostDto.class)).toList();

    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<PostDto> findPostsByTags(List<String> tags) {
        return postRepositoty.findDistinctByTagsNameIgnoreCaseIn(tags)
                .map(x -> modelMapper.map(x, PostDto.class)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        LocalDateTime startOfDay = dateFrom.atStartOfDay();
        LocalDateTime endOfDay = dateTo.atStartOfDay().plusDays(1).minusSeconds(1);

        return postRepositoty.findByDateCreatedBetween(startOfDay, endOfDay)
                .map(x -> modelMapper.map(x, PostDto.class)).toList();
    }

    @Override
    @Transactional
    public void addFileToPost(Long id, MultipartFile file) {
        Post post = postRepositoty.findById(id).orElseThrow(PostNotFoundException::new);

        try {
            AttachedFile attachedFile = new AttachedFile(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            attachedFile.setPost(post);
            fileRepository.save(attachedFile);
        } catch (IOException e) {
           e.printStackTrace();
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<FileDto> getFileFromPost(Long id) {
        Post foundPost = postRepositoty.findById(id).orElseThrow(PostNotFoundException::new);
        return fileRepository.findByPost(foundPost)
                .map(file -> modelMapper.map(file, FileDto.class)).toList();

    }


}
