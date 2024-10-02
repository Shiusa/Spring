package be.vinci.ipl.carts.service;

import be.vinci.ipl.carts.data.CartsItemRepository;
import be.vinci.ipl.carts.data.ProductsProxy;
import be.vinci.ipl.carts.data.UsersProxy;
import be.vinci.ipl.carts.dto.ProductDTO;
import be.vinci.ipl.carts.model.CartItem;
import feign.FeignException;
import feign.FeignException.FeignClientException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartsService {

  @Autowired
  private final UsersProxy usersProxy;

  private final ProductsProxy productsProxy;
  @Autowired
  private final CartsItemRepository repository;

  public CartsService(UsersProxy usersProxy, ProductsProxy productsProxy,
      CartsItemRepository repository) {
    this.usersProxy = usersProxy;
    this.productsProxy = productsProxy;
    this.repository = repository;
  }

  public boolean userExisting(String pseudo) {
    try {
      usersProxy.getOne(pseudo);
      return true;
    } catch (FeignException.FeignClientException e) {
      if (e.status() == 404) {
        return false;
      } else {
        throw e;
      }
    }
  }

  public boolean productExisting(int id) {
    try {
      productsProxy.getOne(id);
      return true;
    } catch (FeignClientException e) {
      if (e.status() == 404) {
        return false;
      } else {
        throw e;
      }
    }
  }

  public boolean cartItemExisting(int productId, String pseudo) {
    return repository.existsByProductIdAndPseudo(productId, pseudo);
  }

  public boolean createCartItem(CartItem cartItem) {
    if (repository.existsByProductIdAndPseudo(cartItem.getProductId(), cartItem.getPseudo())) {
      return false;
    }

    repository.save(cartItem);
    return true;
  }

  public void removeCartItem(String pseudo, int productId) {
    repository.deleteCartItemByProductIdAndPseudo(productId,pseudo);
  }

  public Iterable<ProductDTO> getProductByPseudo(String pseudo) {
    Iterable<CartItem> cartItems = repository.findAllByPseudo(pseudo);
    List<ProductDTO> productList = new ArrayList<>();
    for (CartItem cartItem:cartItems) {
      try {
        ProductDTO product = productsProxy.getOne(cartItem.getProductId());
        if (product != null) {
          productList.add(product);
        }
      } catch (FeignClientException e) {
        throw e;
      }
    }
    return productList;
  }

}
