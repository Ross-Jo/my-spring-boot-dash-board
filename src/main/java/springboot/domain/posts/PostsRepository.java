package springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // SELECT '*' FROM Posts ORDER BY id DESC 와 같이 쿼리할 시 에러 발생,
                                                       // No converter found capable of converting from type ~
    List<Posts> findAllDesc();
}
