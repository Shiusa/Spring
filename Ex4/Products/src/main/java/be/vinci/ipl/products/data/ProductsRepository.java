package be.vinci.ipl.products.data;

import be.vinci.ipl.products.model.Product;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Product, String> {

  boolean existsByNameAndCategoryAndPrice(String name, String category, float price);

  Optional<Product> findTopByOrderByIdDesc();

}
