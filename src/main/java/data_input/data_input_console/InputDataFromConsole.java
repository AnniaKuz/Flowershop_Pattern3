package data_input.data_input_console;

import data_input.DataInput;
import entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@AllArgsConstructor
@Data
public class InputDataFromConsole implements DataInput {
    Scanner scan;
    @Override
    public ProductDTO getProductForSave() {
        System.out.println("Choose product type(FLOWER - 1, TREE -2, DECO - 3)");
        ProductType productType=null;
        switch(scan.next()){
            case "1":
                productType = ProductType.FLOWER;
                break;
            case "2":
                productType = ProductType.TREE;
                break;
            case "3":
                productType = ProductType.DECO;
                break;
        }


        System.out.println("Enter characteristic[flower - color(String), tree - height(double), deco - material(WOOD/PLASTIC)]");
        String info = scan.next();
        System.out.println("Enter product price");
        double price = Double.parseDouble(scan.next());
        return new ProductDTO(productType,price,(Object)info);
    }

    @Override
    public Integer getProductId() {
        System.out.println("Enter product id");
        return scan.nextInt();
    }

    @Override
    public List<Integer> getInvoice() {
        List<Integer> list = new ArrayList<>();
        System.out.println("Enter number products:");
        int number = scan.nextInt();

        for(int i=0;i<number;i++){
            System.out.println("Enter the id of the product");
            list.add(scan.nextInt());
        }
       return list;
    }
}
