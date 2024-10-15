package be.vinci.ipl.gateway.service;

import be.vinci.ipl.gateway.data.AuthenticationProxy;
import be.vinci.ipl.gateway.data.UsersProxy;
import be.vinci.ipl.gateway.dto.CredentialsDTO;
import be.vinci.ipl.gateway.dto.UserDTO;
import be.vinci.ipl.gateway.dto.UserWithCredentialsDTO;
import be.vinci.ipl.gateway.exceptions.BadRequestException;
import be.vinci.ipl.gateway.exceptions.ConflictException;
import be.vinci.ipl.gateway.exceptions.ForbiddenException;
import be.vinci.ipl.gateway.exceptions.NotFoundException;
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

  public UserDTO readOneUser(String pseudo) {
    try {
      return usersProxy.readOne(pseudo);
    } catch (FeignException e) {
      if (e.status() == 404) throw new NotFoundException();
      else throw e;
    }
  }

  public void verify(String token, String expectedPseudo) {
    try {
      String userPseudo = authenticationProxy.verify(token);
      if (!userPseudo.equals(expectedPseudo)) throw new ForbiddenException();
    } catch (FeignException e) {
      if (e.status() == 401) throw new UnauthorizedException();
      else throw e;
    }
  }

  public void updateUser(UserWithCredentialsDTO user) {
    try {
      usersProxy.updateOne(user.getPseudo(), user);
    } catch (FeignException e) {
      if (e.status() == 400) throw new BadRequestException();
      else if (e.status() == 404) throw new NotFoundException();
      else throw e;
    }
  }
}
