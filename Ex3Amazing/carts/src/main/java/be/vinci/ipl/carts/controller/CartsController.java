package be.vinci.ipl.carts.controller;

import be.vinci.ipl.carts.dto.ProductDTO;
import be.vinci.ipl.carts.model.CartItem;
import be.vinci.ipl.carts.service.CartsService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartsController {

  private final CartsService service;

  public CartsController(CartsService service) {
    this.service = service;
  }

  @PostMapping("/carts/users/{pseudo}/products/{productId}")
  public ResponseEntity<Void> addProduct(@PathVariable String pseudo, @PathVariable int productId, CartItem cartItem) {
    if (!service.userExisting(pseudo) || !service.productExisting(productId)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    boolean created = service.createCartItem(cartItem);
    if (!created) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/carts/users/{pseudo}/products/{productId}")
  public ResponseEntity<Void> removeProduct(@PathVariable String pseudo, @PathVariable int productId) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/carts/users/{pseudo}")
  public Iterable<ProductDTO> getProducts(@PathVariable String pseudo) {
    return new ArrayList<>();
  }

  @DeleteMapping("/carts/users/{pseudo}")
  public ResponseEntity<Void> deleteUserCart(@PathVariable String pseudo) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/carts/products/{productId}")
  public ResponseEntity<Void> deleteProductFromAllCart(@PathVariable int productId) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
