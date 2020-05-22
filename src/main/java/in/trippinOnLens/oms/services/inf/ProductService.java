/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.trippinOnLens.oms.services.inf;

import in.trippinOnLens.oms.models.Order;
import in.trippinOnLens.oms.models.Product;
import in.trippinOnLens.oms.models.RequestModel;
import java.util.List;

/**
 *
 * @author trippinOnCode
 */
public interface ProductService {

    public int addProduct(RequestModel request);

    public List<Product> getAllProductForCart(RequestModel request);

    public List<Product> getAllProduct();

    public List<Order> getAllOrders();

    public List<Order> getSingleOrder(String orderId);

    public int completOrder();

    
}
