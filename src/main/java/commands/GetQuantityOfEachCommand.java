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
public class GetQuantityOfEachCommand implements Command{
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
            //flowerShop.addProduct(product,productType);

        });
        int flowers = 0;
        int trees = 0;
        int decos = 0;

        for(int i = 0; i< products.size();i++){
            if(products.get(i).getProductType().toString().equalsIgnoreCase("flower")){
                flowers += 1;
            }else if(products.get(i).getProductType().toString().equalsIgnoreCase("tree")){
                trees += 1;
            }else if(products.get(i).getProductType().toString().equalsIgnoreCase("deco")){
                decos += 1;
            }else{
                System.out.println("Add new ProductType");
            }
        }
        System.out.println("Trees : "+trees+"\nFlowers : "+flowers+"\nDecos : "+decos);
    }
}
