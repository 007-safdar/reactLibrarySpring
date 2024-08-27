package react.library.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import react.library.springboot.entity.Review;
import react.library.springboot.requestmodels.ReviewRequest;
import react.library.springboot.service.ReviewService;
import react.library.springboot.utils.ExtractJWT;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")

public class ReviewController {
    ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService=reviewService;
    }

    @PostMapping("/secure")
    public void postReview(@RequestHeader(value = "Authorization") String token, @RequestBody ReviewRequest reviewRequest) throws Exception{
        String userEmail = ExtractJWT.JWTExtraction(token,"\"sub\"");
        reviewService.postReview(userEmail,reviewRequest);
    }

    @GetMapping("/secure/userReviewed")
    public Boolean userReviewed(@RequestHeader(value = "Authorization") String token, @RequestParam(value = "bookId") Long bookId){
        String userEmail = ExtractJWT.JWTExtraction(token,"\"sub\"");
        return  reviewService.userReviewed(userEmail,bookId);

    }
}
