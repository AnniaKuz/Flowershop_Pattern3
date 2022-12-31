package data_sources.file;

import data_sources.TicketDataSource;
import data_sources.mysql.ProductMySQL;
import entities.Invoice;
import entities.ProductDTO;
import entities.ProductType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketData implements TicketDataSource {
    private static final String FILE_PATH = "product_storage/ticket.txt";
    private static Path path = Paths.get(FILE_PATH);
    private static TicketData instance;

    @Override
    public Integer addInvoice(Invoice invoice) {
        Integer invoiceId = getLastId()+1;
        String res = "\n"+invoiceId+" "+invoice.getTotalProducts()+" "+invoice.getTotalPrice();
        try {
            Files.writeString(path,res, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return invoiceId;
    }

    @Override
    public List <Invoice> getAll() {

        if(!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        List <Invoice> list = null;
        try {
            list = Files.readAllLines(path).stream().map(x->{
                if(!x.isEmpty()){
                    String [] arStr = x.split(" ");
                    Invoice invoice = new Invoice();
                    invoice.setId(Integer.parseInt(arStr[0]));
                    invoice.setTotalProducts(Integer.parseInt(arStr[1]));
                    invoice.setTotalPrice(Double.parseDouble(arStr[2]));
                    return  invoice;
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
        List<Invoice> invoiceList = getAll();
        if(!invoiceList.isEmpty()) {
            return invoiceList.get(invoiceList.size() - 1).getId();
        }
        return 0;
    }

    public static TicketData getInstance(){
        if(instance==null){
            instance=new TicketData();
        }
        return instance;
    }


}
