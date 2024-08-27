package react.library.springboot.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import react.library.springboot.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Page<Review> findByBookId(@RequestParam("bookId") long bookId , Pageable pageable);
    Review findByUserEmailAndBookId(String userEmail , Long bookId );
}
