package be.vinci.ipl.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity(name = "users")
public class User {
    @Id
    @Column(nullable = false)
    private String pseudo;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    /*public boolean invalid() {
        return pseudo == null || pseudo.isBlank() ||
                firstname == null || firstname.isBlank() ||
                lastname == null || lastname.isBlank();
    }*/
}
