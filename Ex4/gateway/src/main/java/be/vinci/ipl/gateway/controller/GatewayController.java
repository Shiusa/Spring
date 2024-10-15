package be.vinci.ipl.gateway.controller;

import be.vinci.ipl.gateway.dto.CredentialsDTO;
import be.vinci.ipl.gateway.service.GatewayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
