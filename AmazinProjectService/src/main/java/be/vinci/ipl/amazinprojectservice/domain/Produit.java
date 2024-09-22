package be.vinci.ipl.amazinprojectservice.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Produit {

  private int id;
  @EqualsAndHashCode.Include
  private String name;
  @EqualsAndHashCode.Include
  private String category;
  @EqualsAndHashCode.Include
  private float price;

}
