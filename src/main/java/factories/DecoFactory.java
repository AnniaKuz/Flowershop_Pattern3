package factories;

/*public interface Product {
    String toString();
}*/


import entities.Deco;
import entities.Material;
import entities.Product;

class DecoFactory implements ProductAbstractFactory {
    public Product create(Integer id, double price, Object productInfo){

        return (Product) new Deco(id,price,Material.valueOf((String) productInfo));
    }
}


