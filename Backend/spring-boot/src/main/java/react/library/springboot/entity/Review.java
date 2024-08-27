package react.library.springboot.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="review")
@Data
public class Review {

    public Review(String userEmail , Date date, double rating , long bookId , String reviewDescription){
        this.userEmail=userEmail;
        this.date=date;
        this.rating=rating;
        this.bookId=bookId;
        this.reviewDescription=reviewDescription;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long id;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="date")
    @CreationTimestamp
    private Date date;

    @Column(name="rating")
    private double rating;

    @Column(name="book_id")
    private long bookId;

    @Column(name="review_description")
    private String reviewDescription;

    public Review() {

    }
}
