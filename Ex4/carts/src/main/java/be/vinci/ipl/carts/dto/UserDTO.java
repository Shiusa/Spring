package be.vinci.ipl.carts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {

  private String pseudo;
  private String firstname;
  private String lastname;

}
