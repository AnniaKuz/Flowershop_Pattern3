package commands;

import data_sources.ProductDataSource;
import entities.FlowerShop;
import entities.Product;
import entities.ProductDTO;
import entities.ProductType;
import factories.ProductAbstractFactory;
import factories.ProductProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTotalValueCommand implements Command{
    ProductDataSource dataSource;
    ProductProvider productProvider;
    FlowerShop flowerShop;
    {
        flowerShop = FlowerShop.getInstance();
    }

    @Override
    public void execute() throws IOException {
        List<ProductDTO> products = dataSource.getAll();
        products.forEach(p->{
            ProductType productType = p.getProductType();
            ProductAbstractFactory productAbstractFactory = productProvider.getProductFactory(productType);
            Product product = productAbstractFactory.create(p.getId(),p.getPrice(),p.getInfo());

        });

        double total = 0.0;
        if(!products.isEmpty()) {
            total=products.stream().filter(product ->product!=null&&
                            (Double)product.getPrice()!=null).mapToDouble(ProductDTO::getPrice).sum();
        }

        System.out.println("Total value is " +total);
    }
}
