package be.vinci.ipl.ex2.service;

import be.vinci.ipl.ex2.data.ProductsRepository;
import be.vinci.ipl.ex2.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

  private final ProductsRepository repository;

  public ProductsService(ProductsRepository repository) {
    this.repository = repository;
  }

  public boolean createOne(Product product) {
    if (repository.existsByNameAndCategoryAndPrice(product.getName(), product.getCategory(),
        product.getPrice())) {
      return false;
    }
    int productId = repository.findTopByOrderByIdDesc().map(Product::getId).orElse(0)+1;
    product.setId(productId);
    repository.save(product);
    return true;
  }

  public Iterable<Product> findAll() {
    return repository.findAll();
  }

  public Product findById(String id) {
    return repository.findById(id).orElse(null);
  }

  public boolean updateOne(Product product) {
    if (repository.existsById(String.valueOf(product.getId()))) {
      repository.save(product);
      return true;
    }
    return false;
  }

}
