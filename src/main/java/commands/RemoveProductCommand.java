package commands;

import data_input.DataInput;
import data_sources.ProductDataSource;
import entities.FlowerShop;
import entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemoveProductCommand implements Command{

    ProductDataSource dataSource;
    DataInput dataInput;
    Scanner scan;
    FlowerShop flowerShop;
    {
        flowerShop = FlowerShop.getInstance();
    }
    @Override
    public void execute() throws IOException {
        flowerShop.getAllProducts();
        int index = introduceIndex();
        dataSource.remove(index);
        flowerShop.removeProduct(index);
    }

    public int introduceIndex(){
        int id = dataInput.getProductId();
        return id;
    }

}
