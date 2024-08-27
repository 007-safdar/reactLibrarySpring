package react.library.springboot.requestmodels;

import lombok.Data;

import java.util.Optional;

@Data
public class ReviewRequest {

    private String userEmail;
    private double Rating;
    private Long BookId;
    private Optional<String> ReviewDescription;

}
