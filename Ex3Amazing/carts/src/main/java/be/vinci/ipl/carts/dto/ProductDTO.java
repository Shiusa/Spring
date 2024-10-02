package be.vinci.ipl.carts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDTO {
  private int id;
  private String name;
  private String category;
  private float price;
}
