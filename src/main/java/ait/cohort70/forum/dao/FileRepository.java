package ait.cohort70.forum.dao;

import ait.cohort70.forum.model.AttachedFile;
import ait.cohort70.forum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface FileRepository extends JpaRepository<AttachedFile, Long> {
    Stream<AttachedFile> findByPost(Post post);
}
