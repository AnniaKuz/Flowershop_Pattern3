package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProductDTO implements Serializable {
    Integer id;
    ProductType productType;
    double price;
    Object info;


    public ProductDTO(ProductType productType, double price, Object info) {
        this.productType = productType;
        this.price = price;
        this.info = info;
    }
}
