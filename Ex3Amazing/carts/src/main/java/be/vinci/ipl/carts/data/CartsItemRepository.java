package be.vinci.ipl.carts.data;

import be.vinci.ipl.carts.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartsItemRepository extends CrudRepository<CartItem, String> {

  boolean existsByProductIdAndPseudo(int productId, String pseudo);

  @Transactional
  void deleteCartItemByProductIdAndPseudo(int productId, String pseudo);

  Iterable<CartItem> findAllByPseudo(String pseudo);

  @Transactional
  void deleteAllByPseudo(String pseudo);

  @Transactional
  void deleteAllByProductId(int productId);

}
