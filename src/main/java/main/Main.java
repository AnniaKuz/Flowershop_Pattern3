package main;
/*
I simplified the menu by putting addProducts commands and removeProduct commands in two because
this way it will be easier to add a new productType in the future.

I could have read file.txt in the beginning and rewrite it in the end, but in my opinion it is better to work
in real time.

There is an option for the program to switch to another way of input information. Right now it s a Scanner,
but usually programs get the information from another sources.

Here I work with txt files but I made the initial base to connect to some DB.

I implement serialization process at the end of the program. I have methods to deserialize Products and Invoices
 that could be implemented in InitShopCommand and ShowAllInvoicesCommand, however, I prioritize the information
 I get from txt.file
 */

import commands.*;
import data_input.data_input_console.InputDataFromConsole;

import data_sources.file.ProductData;
import data_sources.file.TicketData;

import data_sources.mongo_db.ProductMongoDB;
import data_sources.mongo_db.TicketMongoDB;
import data_sources.mysql.ProductMySQL;
import data_sources.mysql.TicketMySQL;
import entities.FlowerShop;
import factories.ProductProvider;
import serialization.Serialization;

import java.util.Scanner;


public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        SwitchCommand switchCommand = new SwitchCommand();
        InitShopCommand initShopCommand = new InitShopCommand();
        initShopCommand.setProductProvider(new ProductProvider());
        switchCommand.registerCommand("1", initShopCommand);

        AddProductCommand addProductCommand = new AddProductCommand();
        addProductCommand.setDataInput(new InputDataFromConsole(scan));
        addProductCommand.setProductProvider(new ProductProvider());
        switchCommand.registerCommand("2", addProductCommand);

        RemoveProductCommand removeProductCommand = new RemoveProductCommand();
        removeProductCommand.setDataInput(new InputDataFromConsole(scan));
        switchCommand.registerCommand("3", removeProductCommand);

        ShowProductsCommand showProductsCommand = new ShowProductsCommand();
        switchCommand.registerCommand("4", showProductsCommand);

        GetQuantityOfEachCommand getQuantityOfEach = new GetQuantityOfEachCommand();
        getQuantityOfEach.setProductProvider(new ProductProvider());
        switchCommand.registerCommand("5", getQuantityOfEach);

        GetTotalValueCommand getTotalValue = new GetTotalValueCommand();
        getTotalValue.setProductProvider(new ProductProvider());
        switchCommand.registerCommand("6", getTotalValue);

        AddTicketCommand addTicketCommand = new AddTicketCommand();
        addTicketCommand.setDataInput(new InputDataFromConsole(scan));
        switchCommand.registerCommand("7", addTicketCommand);

        ShowAllInvoicesCommand showAllInvoices = new ShowAllInvoicesCommand();
        switchCommand.registerCommand("8", showAllInvoices);


        ShowAllEarningsCommand showAllEarnings = new ShowAllEarningsCommand();
        switchCommand.registerCommand("9", showAllEarnings);

        Exit exit = new Exit();
        switchCommand.registerCommand("0", exit);


        String option = null;

        writeName(new FlowerShop());
        setDataSource(chooseDataSource(), initShopCommand, addProductCommand, removeProductCommand,
                showProductsCommand, getQuantityOfEach, getTotalValue, addTicketCommand, showAllInvoices, showAllEarnings );


        System.out.println("Do 1.Create a flower shop, first!");
        do {
            System.out.println("Choose an option: \n"
                    + "1.Create a flower shop \n"
                    + "2.Add product \n"
                    + "3.Remove a product\n"
                    + "4.Show all the products\n"
                    + "5.Show stock quantity of each product\n"
                    + "6.Show total value of a flower shop\n"
                    + "7.Create a new ticket with multiple products\n"
                    + "8.Show all the purchase\n"
                    + "9.Show all the earnings\n"
                    + "0.Exit");
            option = scan.next();
            switchCommand.execute(option);

        } while (Integer.parseInt(option) != 0);

        doSerialization();
    }
    public static void writeName(FlowerShop flowerShop){
        System.out.println("Write a name for the flowerShop");
        flowerShop.setName(scan.next());
    }

    public static void doSerialization(){
        Serialization ser = new Serialization();
        ser.serializeProducts();
        ser.serializeInvoices();
        System.out.println("Serialization is done successfully");
    }

    public static String chooseDataSource(){
        String ans = "";
        boolean done = false;
        do {
            System.out.println("What kind of DataSource you want to use? TXTFile/MySQL/MongoDB");
            ans = scan.next();
            if (!(ans.equalsIgnoreCase("MySQL") || ans.equalsIgnoreCase("TXTFile")
                    ||ans.equalsIgnoreCase("Mongodb"))) {
                System.out.println("DataSource not found");
            }else{
               done = true;
            }
        }while(!done);
        return ans;
    }

    public static void setDataSource(String dataSource, InitShopCommand initShopCommand,
                     AddProductCommand addProductCommand, RemoveProductCommand removeProductCommand,
                     ShowProductsCommand showProductsCommand, GetQuantityOfEachCommand getQuantityOfEach,
                                     GetTotalValueCommand getTotalValueCommand, AddTicketCommand addTicketCommand,
                                     ShowAllInvoicesCommand showAllInvoices, ShowAllEarningsCommand showAllEarnings
                                            ){
        if(dataSource.equalsIgnoreCase("TXTFile")){
            initShopCommand.setDataSource(ProductData.getInstance());
            addProductCommand.setDataSource(ProductData.getInstance());
            removeProductCommand.setDataSource(ProductData.getInstance());
            showProductsCommand.setDataSource(ProductData.getInstance());
            getQuantityOfEach.setDataSource(ProductData.getInstance());
            getTotalValueCommand.setDataSource(ProductData.getInstance());
            addTicketCommand.setTicketData(TicketData.getInstance());
            addTicketCommand.setDataSource(ProductData.getInstance());
            showAllInvoices.setTicketData(TicketData.getInstance());
            showAllEarnings.setTicketData(TicketData.getInstance());


        }else if(dataSource.equalsIgnoreCase("MySQL")){
            initShopCommand.setDataSource(ProductMySQL.getInstance());
            addProductCommand.setDataSource(ProductMySQL.getInstance());
            removeProductCommand.setDataSource(ProductMySQL.getInstance());
            showProductsCommand.setDataSource(ProductMySQL.getInstance());
            getQuantityOfEach.setDataSource(ProductMySQL.getInstance());
            getTotalValueCommand.setDataSource(ProductMySQL.getInstance());
            addTicketCommand.setTicketData(TicketMySQL.getInstance());
            addTicketCommand.setDataSource(ProductMySQL.getInstance());
            showAllInvoices.setTicketData(TicketMySQL.getInstance());
            showAllEarnings.setTicketData(TicketMySQL.getInstance());
            System.out.println("Check mysql.properties and verify the information");

        }else if(dataSource.equalsIgnoreCase("Mongodb")){
            initShopCommand.setDataSource(ProductMongoDB.getInstance());
            addProductCommand.setDataSource(ProductMongoDB.getInstance());
            removeProductCommand.setDataSource(ProductMongoDB.getInstance());
            showProductsCommand.setDataSource(ProductMongoDB.getInstance());
            getQuantityOfEach.setDataSource(ProductMongoDB.getInstance());
            getTotalValueCommand.setDataSource(ProductMongoDB.getInstance());
            addTicketCommand.setTicketData(TicketMongoDB.getInstance());
            addTicketCommand.setDataSource(ProductMongoDB.getInstance());
            showAllInvoices.setTicketData(TicketMongoDB.getInstance());
            showAllEarnings.setTicketData(TicketMongoDB.getInstance());
            System.out.println("Make sure you've created database 'flowershop' with collections 'products' and 'invoices'" +
                    "using MongoDB Compass. Check mongo.properties and verify the information ");

        }
    }

}