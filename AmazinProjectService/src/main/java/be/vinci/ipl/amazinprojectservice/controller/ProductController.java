package be.vinci.ipl.amazinprojectservice.controller;

import be.vinci.ipl.amazinprojectservice.domain.Produit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
public class ProductController {

  private static final Map<Integer, Produit> produits = new HashMap<>();

  static {
    produits.put(1,new Produit(
        1,
        "Produit1",
        "Categorie1",
        10.0f
    ));
    produits.put(3,new Produit(
        3,
        "Produit3",
        "Categorie1",
        20.0f
    ));
  }

  private int createNewId() {
    Optional<Integer> lastId = produits.keySet().stream().max(Integer::compareTo);
    return lastId.orElse(0);
  }

  @GetMapping("/produits")
  public Iterable<Produit> readAll() {
    return produits.values();
  }

  @GetMapping("/produits/{productId}")
  public Produit readOne(@PathVariable int productId) {

    return produits.get(productId);
  }

  @PostMapping("/produits")
  public ResponseEntity<Void> createOne(@RequestBody Produit produit) {
    for (Produit prod:produits.values()) {
      if (prod.equals(produit)) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
    }

    int productId = createNewId()+1;
    produit.setId(productId);
    produits.put(productId,produit);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("produits/{prductId}")
  public ResponseEntity<Void> updateOne(@PathVariable int prductId, @RequestBody Produit produit) {
    if (!produits.containsKey(prductId) || produits.get(prductId)==null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    if (produit.getPrice()<0f || produit.getName().isBlank() || produit.getCategory().isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    produits.put(prductId,produit);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("produits")
  public ResponseEntity<Void> deleteAll() {
    produits.clear();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("produits/{productId}")
  public ResponseEntity<Void> deleteOne(@PathVariable int productId) {
    if (!produits.containsKey(productId)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    produits.remove(productId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
