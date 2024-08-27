package react.library.springboot.service;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import react.library.springboot.dao.BookRepository;
import react.library.springboot.dao.CheckoutRepository;
import react.library.springboot.entity.Book;
import react.library.springboot.entity.Checkout;
import react.library.springboot.responsemodels.ShelfPageResponseModel;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class BookService {
    private BookRepository bookRepository;
    private CheckoutRepository checkoutRepository;


    public BookService(BookRepository bookRepository,CheckoutRepository checkoutRepository){
        this.bookRepository=bookRepository;
        this.checkoutRepository=checkoutRepository;
    }

    public void returnBook(String userEmail, long bookId) throws Exception{
       Optional<Book> book = bookRepository.findById(bookId);
       Checkout checkout = checkoutRepository.findByUserEmailAndBookId(userEmail,bookId);

       if(!book.isPresent() || checkout == null){
           throw new Exception("This book does not exist and hence cannot be returned");
       }

       //Delete checkout
       checkoutRepository.deleteById(checkout.getId());
       //increase the book count
        book.get().setCopiesAvailable(book.get().getCopiesAvailable()+1);
       bookRepository.save(book.get());
    }

    public void renewBook(String userEmail, Long bookId)throws Exception{
        Checkout checkout= checkoutRepository.findByUserEmailAndBookId(userEmail,bookId);
        if(checkout == null) {
            throw new Exception("This book does not exist and hence cannot be renewed");
        }
        LocalDate localDate = LocalDate.parse(checkout.getReturnDate());
        localDate = localDate.plusDays(7);
        checkout.setReturnDate(localDate.toString());

        }

    public Book checkoutBook (String userEmail , long bookId) throws Exception{
        Optional<Book> book = bookRepository.findById(bookId);
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail,bookId);

        if(!book.isPresent() || validateCheckout !=null || book.get().getCopiesAvailable() <=0){
            throw new Exception("Book does not exist or already checked out by the user");
        }


        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepository.save(book.get());

        Checkout checkout = new Checkout(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId()
        );
        checkoutRepository.save(checkout);
        return book.get();

    }

    public boolean isCheckedOut(String userEmail, Long bookId){
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail,bookId);
        return validateCheckout!=null;
    }

    public int currentLoans(String userEmail){
        return checkoutRepository.findBooksByUserEmail(userEmail).size();
    }


    public List<ShelfPageResponseModel> shelfPageLoans(String userEmail) throws ParseException {
        List<ShelfPageResponseModel> shelfPageList= new ArrayList<ShelfPageResponseModel>();

        List<Checkout> checkedOutList = checkoutRepository.findBooksByUserEmail(userEmail);
        List<Long> bookIdList = new ArrayList<Long>();
        for(Checkout i : checkedOutList){
            bookIdList.add(i.getBookId());
        }
        List<Book> bookList = bookRepository.findBooksByBookIds(bookIdList);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for(Book b: bookList){
            Optional<Checkout> checkout =checkedOutList.stream().filter(x-> x.getBookId() == b.getId()).findFirst();
            if(checkout.isPresent()){
                //Date processing to find days left then in the end add it to shelfpagelist
                Date d1 = sdf.parse(checkout.get().getReturnDate());
                Date d2 = sdf.parse(LocalDate.now().toString());
                TimeUnit time = TimeUnit.DAYS;
                long difference_in_time = time.convert(d1.getTime()-d2.getTime(),TimeUnit.MILLISECONDS);
                shelfPageList.add(new ShelfPageResponseModel(b,(int)difference_in_time));
            }
        }
        return shelfPageList;

    }

}
