package commands;

import data_input.DataInput;
import data_sources.ProductDataSource;
import data_sources.TicketDataSource;
import data_sources.file.ProductData;
import data_sources.file.TicketData;
import entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import serialization.Serialization;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTicketCommand implements Command{
    Serialization serialization;
  DataInput dataInput;
  ProductDataSource dataSource;
  TicketDataSource ticketData;
  FlowerShop flowerShop;
    {
        flowerShop = FlowerShop.getInstance();
    }
    @Override
    public void execute() {
        List<Integer> listId = dataInput.getInvoice();
        List<ProductDTO> products = dataSource.getAll();
       List<ProductDTO> invoiceProducts = listId.stream().map(

             id->{
                 for(ProductDTO p:products){
                     if(p.getId().equals(id)){
                         return p;
                     }
                 }
               return null;
             }

       ).toList();
       int totalNumber = invoiceProducts.size();
       double totalPrice  = invoiceProducts.stream().reduce(0.0,(x,y)->x+y.getPrice(),Double::sum);
Invoice invoice = new Invoice(invoiceProducts,totalNumber,totalPrice);
Integer invoiceId =ticketData.addInvoice(invoice);
invoice.setId(invoiceId);
       invoice.confirmInvoice();

       for(int i = 0; i<invoiceProducts.size();i++){
           int id = invoiceProducts.get(i).getId();
           dataSource.remove(id);
       }
    }
}
