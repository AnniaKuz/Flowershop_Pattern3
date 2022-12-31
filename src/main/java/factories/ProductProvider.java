package factories;


import entities.ProductType;

public class ProductProvider {
    public ProductAbstractFactory getProductFactory(ProductType productType) {
        switch (productType) {
            case FLOWER:
                return new FlowerFactory();
            case TREE:
                return new TreeFactory();

            case DECO:
                return new DecoFactory();
            default:
                System.out.println("This product type does not exist");

        }
        return null;
    }
}