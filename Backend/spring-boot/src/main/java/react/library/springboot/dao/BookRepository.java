package react.library.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import react.library.springboot.entity.Book;
public interface BookRepository extends JpaRepository < Book , Long> {

}
