package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Ticket {
    private static Ticket instance;

    private List<Invoice> ticketStorage;

    {
        ticketStorage = new ArrayList<>();
    }


    public void printAllTicket(){
        ticketStorage.forEach(System.out::println);
    }

    public  void addInvoice(Invoice invoice){
        ticketStorage.add(invoice);
    }

    public static Ticket getInstance(){
        if(instance==null){
            instance=new Ticket();
        }
        return instance;
    }
}
