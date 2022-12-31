package data_sources.mysql;

import data_sources.ProductDataSource;
import entities.ProductDTO;
import entities.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Data
public class ProductMySQL implements ProductDataSource<ProductDTO>{

    private static ProductMySQL instance;

    @Override
    public Integer add(ProductDTO product) {

        try {
            Connection con = DBConnector.getConnection();
            String sql = "INSERT INTO product (productType, info, price) VALUES(?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,String.valueOf(product.getProductType()));
            stmt.setObject(2,product.getInfo());
            stmt.setDouble(3,product.getPrice());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

            return product.getId();
    }



    @Override
    public void remove(Integer id) {
        try {
            Connection con = DBConnector.getConnection();
            String sql = "DELETE FROM product WHERE id ="+id+"";
            PreparedStatement stmt = con.prepareStatement(sql);
            int rowAffected = stmt.executeUpdate(sql);
            System.out.println("Rows affected: "+rowAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductDTO> products = new ArrayList<>();

        try {


            Connection con = DBConnector.getConnection();;
            if (con != null) {
                System.out.println("Connected to the dataBase");
            }

            String sql = "SELECT * from product";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(resultSet.getInt("id"));
                product.setProductType(ProductType.valueOf(resultSet.getString("productType")));//, ProductType.class));
                product.setPrice(resultSet.getDouble("price"));
                product.setInfo(resultSet.getObject("info"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;

    }

    @Override
    public ProductDTO getProductById(Integer id) {
        List<ProductDTO> products = getAll();

        List<ProductDTO>productsFilter = products.stream().filter(x->x.getId().equals(id)).toList();
        if(productsFilter.isEmpty()){
            System.out.println("Product with this id does not exist");
            return null;
        }
        return productsFilter.get(0);
    }
    
    public static ProductMySQL getInstance(){
        if(instance==null){
            instance=new ProductMySQL();
        }
        return instance;
    }
}
