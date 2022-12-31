package data_sources.file;

import data_sources.ProductDataSource;
import data_sources.mysql.ProductMySQL;
import entities.ProductDTO;
import entities.ProductType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductData implements ProductDataSource<ProductDTO> {
    private static final String FILE_PATH = "product_storage/products.txt";
    private static Path path = Paths.get(FILE_PATH);
    private static ProductData instance;

    @Override
    public Integer add(ProductDTO product)  {
    Integer id=getLastId()+1;
        product.setId(id);
        String res = product.getId()+" "+product.getProductType()+" "+product.getPrice()+" "+product.getInfo()+"\n";

        try {
            Files.writeString(path,res, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return id;



    }


    @Override
    public void remove(Integer id) {
        List<ProductDTO> products = getAll();

        ProductDTO p = getProductById(id);

        if(p == null){
            System.out.println("Product with id" + id+ " does not exist");
        }
        else {

            int index = products.indexOf(p);

            try {
                rewriteFile( products.stream().filter(x->!(x.getId().equals(id))).toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
  private void rewriteFile(List<ProductDTO> products) throws IOException {
       List <String> productsForWrite = products.stream().map(product->
               product.getId()+" "+product.getProductType()+
                       " "+product.getPrice()+" "+product.getInfo()).toList();

      //System.out.println(productsForWrite);
      Files.delete(path);
      Files.createFile(path);
      Files.write(path, productsForWrite, StandardOpenOption.WRITE);

  }


    @Override
    public List<ProductDTO> getAll() {
        Path path = Paths.get(FILE_PATH);
        if(!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        List <ProductDTO> list = null;
        try {
            list = Files.readAllLines(path).stream().map(x->{
                if(!x.isEmpty()){
                    String [] arStr = x.split(" ");
                    ProductDTO product = new ProductDTO(Integer.parseInt(arStr[0]),
                            ProductType.valueOf(arStr[1]),Double.parseDouble(arStr[2]),String.valueOf(arStr[3]));
                    return  product;
            }
            else{
                return null;
                }
            }).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.stream().filter(x->x!=null).toList();

    }
public Integer getLastId(){
        List<ProductDTO> products = getAll();
        if(!products.isEmpty()) {
            return products.get(products.size() - 1).getId();
        }
        return 0;
}


    @Override
    public ProductDTO getProductById(Integer id) {
        List<ProductDTO> products = getAll();

        List<ProductDTO>productsFilter = products.stream().filter(x->x.getId().equals(id)).toList();
        if(productsFilter.isEmpty()){
            System.out.println("Product with this id does not exist");
        return null;
        }
        return productsFilter.get(0);
    }

    public static ProductData getInstance(){
        if(instance==null){
            instance=new ProductData();
        }
        return instance;
    }



}
