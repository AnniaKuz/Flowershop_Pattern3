package data_sources;

import entities.Invoice;

import java.util.List;
import java.util.Map;

public interface TicketDataSource {
    Integer addInvoice(Invoice invoice);
    List<Invoice> getAll();
}
