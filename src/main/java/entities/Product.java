package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
//@Data
@ToString
public abstract class Product implements Serializable {
    public Product(double price) {
        this.price = price;
    }

    Integer id;
    double price;

    public Integer getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}
