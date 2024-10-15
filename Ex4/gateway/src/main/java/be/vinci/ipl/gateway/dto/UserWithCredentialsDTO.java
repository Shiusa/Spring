package be.vinci.ipl.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserWithCredentialsDTO {

  private String pseudo;
  private String firstname;
  private String lastname;
  private String password;

}
