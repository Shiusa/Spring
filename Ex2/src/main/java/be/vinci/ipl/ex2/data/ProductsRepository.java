package be.vinci.ipl.ex2.data;

import be.vinci.ipl.ex2.model.Product;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Product, String> {

  boolean existsByNameAndCategoryAndPrice(String name, String category, float price);

  Optional<Product> findTopByOrderByIdDesc();

}
