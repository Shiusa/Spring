package be.vinci.ipl.gateway.controller;

import be.vinci.ipl.gateway.dto.CredentialsDTO;
import be.vinci.ipl.gateway.dto.UserDTO;
import be.vinci.ipl.gateway.dto.UserWithCredentialsDTO;
import be.vinci.ipl.gateway.service.GatewayService;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GatewayController {

  private final GatewayService service;

  public GatewayController(GatewayService service) {
    this.service = service;
  }

  @PostMapping("/auth")
  public String connect(@RequestBody CredentialsDTO credentials) {
    return service.connect(credentials); // throws BadRequestException & UnauthorizedException
  }

  @PostMapping("/users/{pseudo}")
  public ResponseEntity<Void> createUser(@PathVariable String pseudo, @RequestBody UserWithCredentialsDTO user) {
    if (!Objects.equals(user.getPseudo(), pseudo)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    service.createUser(user);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/users/{pseudo}")
  public UserDTO readOneUser(@PathVariable String pseudo) {
    return service.readOneUser(pseudo);
  }

  @PutMapping("/users/{pseudo}")
  public void updateUser(@PathVariable String pseudo, @RequestBody UserWithCredentialsDTO user, @RequestHeader("Authorization") String token) {
    if (!Objects.equals(user.getPseudo(), pseudo)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    service.verify(token, pseudo); // throws UnauthorizedException & ForbiddenException

    service.updateUser(user); // throws BadRequestException & NotFoundException
  }

}
