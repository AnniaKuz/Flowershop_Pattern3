package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Flower extends Product{
    private String color;

    public Flower(double price, String color) {
        super(price);
        this.color = color;
    }

    public Flower(Integer id, double price, String color) {
        super(id, price);
        this.color = color;
    }
}
