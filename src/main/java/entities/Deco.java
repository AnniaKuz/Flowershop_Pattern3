package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Deco extends Product{
    private Material material;

    public Deco(double price,Material material) {
        super(price);
        this.material = material;
    }

    public Deco(Integer id, double price, Material material) {
        super(id, price);
        this.material = material;
    }
}