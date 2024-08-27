package react.library.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import react.library.springboot.dao.BookRepository;
import react.library.springboot.entity.Book;
import react.library.springboot.entity.Checkout;
import react.library.springboot.responsemodels.ShelfPageResponseModel;
import react.library.springboot.service.BookService;
import react.library.springboot.utils.ExtractJWT;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }


    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestHeader(value="Authorization") String token,@RequestParam Long bookId) throws Exception{
        String userEmail = ExtractJWT.JWTExtraction(token,"\"sub\"");
        return bookService.checkoutBook(userEmail,bookId);
    }

//    @PutMapping("/checkout")
//    public Book checkoutBook(String token,@RequestParam Long bookId) throws Exception{
//        String userEmail = "testuser@email.com";
//        return bookService.checkoutBook(userEmail,bookId);
//    }



    @GetMapping("/secure/ischeckedout/byuser")
    public boolean isCheckedOut(@RequestHeader(value="Authorization") String token,
    @RequestParam Long bookId) throws Exception{
        String userEmail = ExtractJWT.JWTExtraction(token, "\"sub\"");
        return bookService.isCheckedOut(userEmail,bookId);
    }

    @GetMapping("/secure/currentloans/byuser")
    public int currentLoanCount(@RequestHeader(value="Authorization") String token) throws Exception{
        String userEmail = ExtractJWT.JWTExtraction(token, "\"sub\"");
        return bookService.currentLoans(userEmail);
    }

    @GetMapping("/secure/loans/byuser")
    public List<ShelfPageResponseModel> shelfLoanBooks(@RequestHeader(value="Authorization") String token) throws Exception{
        String userEmail = ExtractJWT.JWTExtraction(token, "\"sub\"");
        return bookService.shelfPageLoans(userEmail);
    }

    @PutMapping("/secure/returnbook/delete")
    public void returnbook(@RequestHeader(value="Authorization")String token , @RequestParam long bookId) throws Exception{
        String userEmail = ExtractJWT.JWTExtraction(token, "\"sub\"");
        bookService.returnBook(userEmail,bookId);
    }

    @PutMapping("/secure/renew/book")
    public void renewbook(@RequestHeader(value="Authorization") String token, @RequestParam Long bookId) throws Exception{
        String userEmail = ExtractJWT.JWTExtraction(token, "\"sub\"");
        bookService.renewBook(userEmail,bookId);
    }

}
