package ait.cohort70.security;

import ait.cohort70.forum.dao.PostRepositoty;
import ait.cohort70.forum.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomWebSecurity {
    private final PostRepositoty postRepositoty;

    public boolean isPostOwner(String username, String postId){
        try {
            Long id = Long.parseLong(postId);
            Post post = postRepositoty.findById(id).orElse(null);
            return post!= null && post.getAuthor().equalsIgnoreCase(username);
        }catch (NumberFormatException e) {
            return false;
        }

    }
}
