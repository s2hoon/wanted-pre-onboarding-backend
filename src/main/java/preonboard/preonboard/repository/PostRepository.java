package preonboard.preonboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboard.preonboard.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
