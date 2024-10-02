package be.vinci.ipl.carts.data;

import be.vinci.ipl.carts.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "users")
public interface UsersProxy {

  @GetMapping("/users/{pseudo}")
  UserDTO getOne(@PathVariable String pseudo);

}
