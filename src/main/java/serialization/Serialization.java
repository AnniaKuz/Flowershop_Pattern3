package serialization;

import entities.FlowerShop;
import entities.Invoice;
import entities.Product;
import entities.Ticket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization {
    FlowerShop flowerShop;
    Ticket ticket;
    {
        flowerShop = FlowerShop.getInstance();
        ticket = Ticket.getInstance();
    }

    public void serializeProducts(){
        if(flowerShop.getProducts().isEmpty()) {
            throw new IllegalArgumentException("The productList is empty");
        }

        try
        {
            FileOutputStream fos = new FileOutputStream("productsData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(flowerShop.getProducts());
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void deserializeProducts() {
         ArrayList<Product> products = (ArrayList) flowerShop.getProducts();

        try {
            FileInputStream fis = new FileInputStream("productsData");
            ObjectInputStream ois = new ObjectInputStream(fis);

            products = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }
    public void serializeInvoices() {
        if (ticket.getTicketStorage().isEmpty()) {
            throw new IllegalArgumentException("The ticketStorage is empty");
        }
        try {
            FileOutputStream fos = new FileOutputStream("invoicesData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ticket.getTicketStorage());
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void deserializeInvoices() {
        try
        {
            FileInputStream fis = new FileInputStream("invoicesData");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Invoice> invoices = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();

        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
        }

    }

}

