package react.library.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="history")
@Entity
public class History {

    public History(String userEmail, String checkoutDate , String returnDate , String title , String author , String description , String image){
        this.userEmail=userEmail;
        this.checkoutDate=checkoutDate;
        this.returnDate=returnDate;
        this.title=title;
        this.author=author;
        this.description = description;
        this.image =image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="checkout_date")
    private String checkoutDate;

    @Column(name="returned_date")
    private String returnDate;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="description")
    private String description;

    @Column(name="img")
    private String image;

}
