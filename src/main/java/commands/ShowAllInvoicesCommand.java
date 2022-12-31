package commands;

import data_sources.TicketDataSource;
import data_sources.file.TicketData;
import entities.FlowerShop;
import entities.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import serialization.Serialization;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ShowAllInvoicesCommand implements Command{
    Serialization serialization;

    TicketDataSource ticketData;


    private static final String FILE_PATH = "product_storage/ticket.txt";
    private static Path path = Paths.get(FILE_PATH);

    @Override
    public void execute() throws IOException {
        //serialization.deserializeInvoices();

        List<Invoice> ticket= ticketData.getAll();

        ticket.forEach(x->
                System.out.println("Id:"+x.getId()+"; number products: "+x.getTotalProducts()+"; total price: "+ x.getTotalPrice()));

    }
}
