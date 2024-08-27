package react.library.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="checkout")
@Data
public class Checkout {

    public Checkout(){

    }

    public Checkout(String userEmail, String CheckoutDate , String ReturnDate,Long bookId){
        this.userEmail=userEmail;
        this.checkOutDate=CheckoutDate;
        this.ReturnDate=ReturnDate;
        this.bookId=bookId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="checkout_date")
    private String checkOutDate;

    @Column(name="return_date")
    private String ReturnDate;

    @Column(name="book_id")
    private Long bookId;




}
