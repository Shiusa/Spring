package be.vinci.ipl.carts.data;

import be.vinci.ipl.carts.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartsItemRepository extends CrudRepository<CartItem, String> {

  boolean existsByProductIdAndPseudo(int productId, String pseudo);

}
