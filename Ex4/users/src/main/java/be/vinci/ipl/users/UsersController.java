package be.vinci.ipl.users;

import be.vinci.ipl.users.dto.UserWithUnsafeCredentialsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
public class UsersController {

    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @PostMapping("/users/{pseudo}")
    public ResponseEntity<Void> createOne(@PathVariable String pseudo, @RequestBody UserWithUnsafeCredentialsDTO user) {
        if (!Objects.equals(user.getPseudo(), pseudo)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        /*if (user.getPseudo()==null || user.getPseudo().isBlank() || user.getFirstname()==null || user.getFirstname().isBlank() ||
        user.getLastname()==null || user.getLastname().isBlank() || user.getPassword()==null || user.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }*/
        if (user.invalid()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        /*if (!user.getUser().getPseudo().equals(user.getUnsafeCredentials().getPseudo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }*/

        boolean created = service.createOne(user);

        if (!created) throw new ResponseStatusException(HttpStatus.CONFLICT);
        else return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/{pseudo}")
    public User readOne(@PathVariable String pseudo) {
        User user = service.readOne(pseudo);

        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else return user;
    }

    @PutMapping("/users/{pseudo}")
    public void updateOne(@PathVariable String pseudo, @RequestBody UserWithUnsafeCredentialsDTO user) {
        if (!Objects.equals(user.getPseudo(), pseudo)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        /*if (user.getPseudo()==null || user.getPseudo().isBlank() || user.getFirstname()==null || user.getFirstname().isBlank() ||
            user.getLastname()==null || user.getLastname().isBlank() || user.getPassword()==null || user.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }*/
        if (user.invalid()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        /*if (!user.getUser().getPseudo().equals(user.getUnsafeCredentials().getPseudo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }*/

        boolean found = service.updateOne(user);
        if (!found) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{pseudo}")
    public void deleteOne(@PathVariable String pseudo) {
        boolean found = service.deleteOne(pseudo);
        if (!found) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
