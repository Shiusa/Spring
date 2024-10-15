package be.vinci.ipl.gateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ForbiddenException extends ResponseStatusException {

  public ForbiddenException() {
    super(HttpStatus.FORBIDDEN);
  }

}
