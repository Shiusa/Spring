package be.vinci.ipl.products.controller;

import be.vinci.ipl.products.model.Product;
import be.vinci.ipl.products.service.ProductsService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ProductsController {

  @Autowired
  private final ProductsService service;
  private static final Map<Integer, Product> products = new HashMap<>();

  static {
    products.put(1,new Product(
        1,
        "Produit1",
        "Categorie1",
        10.0f
    ));
    products.put(3,new Product(
        3,
        "Produit3",
        "Categorie1",
        20.0f
    ));
  }

  public ProductsController(ProductsService service) {
    this.service = service;
  }

  private int createNewId() {
    Optional<Integer> lastId = products.keySet().stream().max(Integer::compareTo);
    return lastId.orElse(0);
  }

  @GetMapping("/produits")
  public Iterable<Product> readAll() {
    //return products.values();
    return service.findAll();
  }

  @GetMapping("/produits/{productId}")
  public Product readOne(@PathVariable int productId) {
    //return products.get(productId);
    return service.findById(String.valueOf(productId));
  }

  @PostMapping("/produits")
  public ResponseEntity<Void> createOne(@RequestBody Product product) {
    /*for (Product prod: products.values()) {
      if (prod.equals(product)) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
    }*/

    /*int productId = createNewId()+1;
    product.setId(productId);
    products.put(productId, product);*/
    boolean created = service.createOne(product);
    if (!created) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("produits/{productId}")
  public ResponseEntity<Void> updateOne(@PathVariable int productId, @RequestBody Product product) {
    if (product.getId() != 0) {
      if (productId != product.getId()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT);
      }
      if (service.findById(String.valueOf(productId)) == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    /*if (!products.containsKey(productId) || products.get(productId)==null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }*/
    if (product.getPrice()<0f || product.getName().isBlank() || product.getCategory().isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    //products.put(prductId, product);
    boolean updated = service.updateOne(product);
    if (!updated) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("produits")
  public ResponseEntity<Void> deleteAll() {
    //products.clear();
    service.deleteAll();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("produits/{productId}")
  public ResponseEntity<Void> deleteOne(@PathVariable int productId) {
    if (service.findById(String.valueOf(productId)) == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    /*if (!products.containsKey(productId)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }*/
    //products.remove(productId);
    service.deleteById(String.valueOf(productId));
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
