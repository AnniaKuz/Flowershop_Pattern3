package data_sources;

import java.util.List;

public interface ProductDataSource<T>{
    Integer add(T product);
    void remove(Integer id);
    List<T> getAll();
    T getProductById(Integer id);
}
