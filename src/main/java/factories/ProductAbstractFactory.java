package factories;




import entities.Product;

public interface ProductAbstractFactory {
    Product create(Integer id, double price, Object productInfo);

}


