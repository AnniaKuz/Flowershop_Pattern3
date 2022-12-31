package factories;




import entities.Flower;
import entities.Product;

class FlowerFactory implements ProductAbstractFactory {
    @Override
    ///??? float > String
    public Product create(Integer id, double price,Object productInfo){
        return (Product) new Flower(id,price,(String)productInfo);
    }
}


