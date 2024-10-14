package be.vinci.ipl.users.data;

import be.vinci.ipl.users.dto.UnsafeCredentialsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@FeignClient(name = "authentication")
public interface AuthenticationProxy {

  @PostMapping("/authentication/{pseudo}")
  void createOne(@PathVariable String pseudo, @RequestBody UnsafeCredentialsDTO credentials);

  @PutMapping("/authentication/{pseudo}")
  void updateOne(@PathVariable String pseudo, @RequestBody UnsafeCredentialsDTO credentials);

  @DeleteMapping("/authentication/{pseudo}")
  void deleteOne(@PathVariable String pseudo);

}
