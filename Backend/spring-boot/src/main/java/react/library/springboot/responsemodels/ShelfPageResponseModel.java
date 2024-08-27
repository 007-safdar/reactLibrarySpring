package react.library.springboot.responsemodels;

import lombok.Data;
import react.library.springboot.entity.Book;

@Data
public class ShelfPageResponseModel {

    private Book book;
    private int daysLeft;

    public ShelfPageResponseModel(Book book , int daysLeft){
        this.book=book;
        this.daysLeft=daysLeft;
    }

}
