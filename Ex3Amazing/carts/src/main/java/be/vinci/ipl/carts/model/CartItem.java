package be.vinci.ipl.carts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "carts")
public class CartItem {

  @Id @Column(nullable = false) @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private int productId;

  @Column(nullable = false)
  private String pseudo;

  public boolean invalid() {
    return id == 0 ||
        productId == 0 ||
        pseudo == null || pseudo.isBlank();
  }

}
