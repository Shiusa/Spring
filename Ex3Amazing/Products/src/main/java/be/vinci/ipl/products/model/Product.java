package be.vinci.ipl.products.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "products")
public class Product {

  @Id @Column(nullable = false)
  @EqualsAndHashCode.Exclude
  private int id;
  @Column(nullable = false)
  @EqualsAndHashCode.Include
  private String name;
  @Column(nullable = false)
  @EqualsAndHashCode.Include
  private String category;
  @Column(nullable = false)
  @EqualsAndHashCode.Include
  private float price;

}
