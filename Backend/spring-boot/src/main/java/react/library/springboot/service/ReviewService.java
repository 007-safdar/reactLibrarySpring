package react.library.springboot.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import react.library.springboot.dao.BookRepository;
import react.library.springboot.dao.ReviewRepository;
import react.library.springboot.entity.Book;
import react.library.springboot.entity.Review;
import react.library.springboot.requestmodels.ReviewRequest;

import java.time.LocalDate;
import java.util.Date;

@Service
@Transactional
public class ReviewService {
    private ReviewRepository reviewRepository;
    private BookRepository bookRepository;

    public ReviewService(ReviewRepository reviewRepository , BookRepository bookRepository){
        this.reviewRepository=reviewRepository;
        this.bookRepository= bookRepository;
    }

    public void postReview (String userEmail , ReviewRequest reviewRequest)throws Exception{
        //Checking the db if the submitted review is already present by using findByEmailAndBookId
        Review newReview= reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());

        if(newReview!= null){
            throw new Exception("This review already exists");
        }

        Review review = new Review();
                review.setUserEmail(userEmail);
                review.setBookId(reviewRequest.getBookId());
                review.setRating(reviewRequest.getRating());
                if(reviewRequest.getReviewDescription().isPresent()) {
                    review.setReviewDescription(reviewRequest.getReviewDescription().map(
                            Object::toString
                    ).orElse(null));
                }
                review.setDate(new Date());
        reviewRepository.save(review);

    }

    public boolean userReviewed (String userEmail , Long bookId){
        return reviewRepository.findByUserEmailAndBookId(userEmail,bookId) !=null;
    }

}
