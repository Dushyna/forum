package ait.cohort70.forum.dao;


import ait.cohort70.forum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface PostRepositoty extends JpaRepository<Post, Long> {
    Stream<Post> findByAuthorIgnoreCase(String author);
    Stream<Post> findDistinctByTagsNameIgnoreCaseIn(List<String> tags);
    Stream<Post> findByDateCreatedBetween(LocalDateTime dateFrom, LocalDateTime dateTo);
}
