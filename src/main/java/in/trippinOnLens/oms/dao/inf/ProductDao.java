/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.trippinOnLens.oms.dao.inf;

import in.trippinOnLens.oms.models.Order;
import in.trippinOnLens.oms.models.Product;
import java.util.List;

/**
 *
 * @author trippinOnCode
 */
public interface ProductDao {

    public Product getProductDetail(String productId);

    public int checkOrderForId(String userId);

    public int insertOrderDetail(String productId, int orderId, int qty);

    public int createOrderAndInsert(String productId, String userId , int qty);

    public List<Product> getAllProductForOrderForUserId(String userId);

    public List<Product> getAllProduct();

    public List<Order> getAllOrders();

    public List<Order> getSingleOrder(String orderId);

    public int completeOrder();
    
}
