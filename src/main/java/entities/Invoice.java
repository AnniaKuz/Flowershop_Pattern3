package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class Invoice implements Serializable {
    Integer id;
    List<ProductDTO> products;
    int totalProducts;
    double totalPrice;

    public Invoice(List<ProductDTO> products, int totalProducts, double totalPrice) {
        this.products = products;
        this.totalProducts = totalProducts;
        this.totalPrice = totalPrice;
    }


    public Invoice(Integer id, Integer totalProducts, Double totalPrice) {
    }

    public Invoice(Integer id, List<ProductDTO> products) {
        this.id = id;
        this.products = products;
    }

     Ticket ticket;
    {
        products = new ArrayList<>();
       ticket =  Ticket.getInstance();
        }


    public void confirmInvoice(){
        ticket.addInvoice(this);

    }

    //???
    public double calculateInvoice(){
    double total = 0;
    if(!products.isEmpty()){
        for(int i = 0; i<=products.size();i++){
            total = products.get(i).getPrice()+ total;
        }
    }else{
        System.out.println("ProductList is empty");
    }
    return total;
    }


    }

