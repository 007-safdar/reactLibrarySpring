package react.library.springboot.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import react.library.springboot.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository < Book , Long> {
   Page<Book> findByTitleContaining(@RequestParam("title")String title, Pageable pageable);
    Page<Book> findByCategory(@RequestParam("category") String category , Pageable pageable);
    //JPQL Query we need to use alias names here
    /*
    What's happening here is we're using Param here which will get replaced by the list. we need to add:
    other examples include

    bookService
    List<Book> books = bookRepository.findByAuthorAndYear("J.K. Rowling", 1997);
    bookRepository
    @Query(select b from books b where b.auther = :auther and b.year =:year)
    List<Book> findBooksByAuthorAndYear(@Param("author") String Author , @Paran("year") int year)
     */
    @Query("select o from Book o where id in :book_ids" )
    List<Book>findBooksByBookIds(@Param("book_ids") List<Long> bookIds);


}
