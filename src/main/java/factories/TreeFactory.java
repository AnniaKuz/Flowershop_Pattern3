package factories;

/*public interface Product {
    String toString();
}*/


import entities.Product;
import entities.Tree;

class TreeFactory implements ProductAbstractFactory {
    public Product create(Integer id, double price, Object productInfo) {

        return (Product) new Tree(id, price, Double.parseDouble((String)productInfo));
    }
}

