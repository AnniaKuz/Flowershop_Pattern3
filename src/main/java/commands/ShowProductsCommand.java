package commands;

import data_sources.ProductDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowProductsCommand implements  Command{
    ProductDataSource dataSource;
    @Override
    public void execute() {

        System.out.println("Total amount of products :" +dataSource.getAll().size());
        System.out.println(dataSource.getAll());

    }
}
