package be.vinci.ipl.carts.data;

import be.vinci.ipl.carts.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "products")
public interface ProductsProxy {

  @GetMapping("/products/{productId}")
  ProductDTO getOne(@PathVariable int productId);

}
