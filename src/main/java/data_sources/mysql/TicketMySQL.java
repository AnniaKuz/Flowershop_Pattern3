package data_sources.mysql;

import data_sources.TicketDataSource;
import entities.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
//@AllArgsConstructor
@Data
public class TicketMySQL implements TicketDataSource {

    private static TicketMySQL instance;

    @Override
    public Integer addInvoice(Invoice invoice) {
        try {
            Connection con = DBConnector.getConnection();;
            String sql = "INSERT INTO invoice (totalProducts, totalPrice) VALUES(?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,invoice.getTotalProducts());
            stmt.setDouble(2, invoice.getTotalPrice());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return invoice.getId();
    }

    @Override
    public List<Invoice> getAll() {
    List<Invoice> invoices = new ArrayList<>();
        try {
            Connection con = DBConnector.getConnection();;
            String sql = "SELECT * from invoice";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(resultSet.getInt("id"));
                invoice.setTotalProducts(resultSet.getInt("totalProducts"));
                invoice.setTotalPrice(resultSet.getDouble("totalPrice"));
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return invoices;
    }

    public static TicketMySQL getInstance(){
        if(instance==null){
            instance=new TicketMySQL();
        }
        return instance;
    }
}
