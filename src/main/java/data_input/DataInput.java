package data_input;

import entities.ProductDTO;


import java.util.List;

public interface DataInput{
    public ProductDTO getProductForSave();
    public Integer getProductId();
    public List<Integer> getInvoice();
}
