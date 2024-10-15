package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.dto.CredentialsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
@FeignClient(name = "authentication")
public interface AuthenticationProxy {

  @PostMapping("/authentication/connect")
  String connect(CredentialsDTO credentials);

  @PostMapping("/authentication/verify")
  String verify(String token);
}
