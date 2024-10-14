package be.vinci.ipl.users.dto;

import be.vinci.ipl.users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserWithUnsafeCredentialsDTO {

  /*private User user;
  private UnsafeCredentialsDTO unsafeCredentials;*/

  private String pseudo;
  private String firstname;
  private String lastname;

  private String password;

  public User toUser() {
    return new User(pseudo, firstname, lastname);
  }
  public UnsafeCredentialsDTO toCredentials() {
    return new UnsafeCredentialsDTO(pseudo, password);
  }

  public boolean invalid() {
    return pseudo == null || pseudo.isBlank() ||
        firstname == null || firstname.isBlank() ||
        lastname == null || lastname.isBlank() ||
        password == null || password.isBlank();
  }

}
