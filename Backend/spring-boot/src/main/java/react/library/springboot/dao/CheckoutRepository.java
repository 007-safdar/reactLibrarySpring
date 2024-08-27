package react.library.springboot.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import react.library.springboot.entity.Checkout;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout,Long> {
    Checkout findByUserEmailAndBookId(String userEmail , Long bookId);
    List<Checkout> findBooksByUserEmail(String userEmail);

}
