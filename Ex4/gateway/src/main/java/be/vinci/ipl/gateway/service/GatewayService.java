package be.vinci.ipl.gateway.service;

import be.vinci.ipl.gateway.data.AuthenticationProxy;
import be.vinci.ipl.gateway.data.UsersProxy;
import be.vinci.ipl.gateway.dto.CredentialsDTO;
import be.vinci.ipl.gateway.dto.UserWithCredentialsDTO;
import be.vinci.ipl.gateway.exceptions.BadRequestException;
import be.vinci.ipl.gateway.exceptions.ConflictException;
import be.vinci.ipl.gateway.exceptions.UnauthorizedException;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {

  private final AuthenticationProxy authenticationProxy;
  private final UsersProxy usersProxy;

  public GatewayService(AuthenticationProxy authenticationProxy,
      UsersProxy usersProxy) {
    this.authenticationProxy = authenticationProxy;
    this.usersProxy = usersProxy;
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

  public void createUser(UserWithCredentialsDTO user) {
    try {
      usersProxy.createOne(user.getPseudo(), user);
    } catch (FeignException e) {
      if (e.status() == 400) throw new BadRequestException();
      else if (e.status() == 409) throw new ConflictException();
      else throw e;
    }
  }
}
