package be.vinci.ipl.gateway.service;

import be.vinci.ipl.gateway.data.AuthenticationProxy;
import be.vinci.ipl.gateway.dto.CredentialsDTO;
import be.vinci.ipl.gateway.exceptions.BadRequestException;
import be.vinci.ipl.gateway.exceptions.UnauthorizedException;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {

  private final AuthenticationProxy authenticationProxy;

  public GatewayService(AuthenticationProxy authenticationProxy) {
    this.authenticationProxy = authenticationProxy;
  }

  public String connect(CredentialsDTO credentials) throws BadRequestException, UnauthorizedException {
    try {
      return authenticationProxy.connect(credentials);
    } catch (FeignException e) {
      if (e.status() == 400) throw new BadRequestException();
      else if (e.status() == 401) throw new UnauthorizedException();
      else throw e;
    }
  }

}
