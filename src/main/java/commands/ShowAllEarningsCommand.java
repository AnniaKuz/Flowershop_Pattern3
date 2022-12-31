package commands;

import data_sources.ProductDataSource;
import data_sources.TicketDataSource;
import data_sources.file.ProductData;
import data_sources.file.TicketData;

import entities.FlowerShop;
import entities.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowAllEarningsCommand implements Command{

    TicketDataSource ticketData;

    @Override
    public void execute() throws IOException {
        List<Invoice> ticket= ticketData.getAll();
        Double totalPrice = 0.0;

        for(int i = 0; i<ticket.size();i++){
            totalPrice += ticket.get(i).getTotalPrice();
        }
        System.out.println("Total earnings : "+totalPrice);
    }


}
