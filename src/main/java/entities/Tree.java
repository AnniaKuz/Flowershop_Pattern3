package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tree extends Product {
    private Double height;

    public Tree(double price, Double height) {
        super(price);
        this.height = height;
    }

    public Tree(Integer id, double price, Double height) {
        super(id, price);
        this.height = height;
    }

}
