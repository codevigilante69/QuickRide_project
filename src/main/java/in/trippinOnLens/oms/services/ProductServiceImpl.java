/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.trippinOnLens.oms.services;

import in.trippinOnLens.oms.dao.inf.ProductDao;
import in.trippinOnLens.oms.models.Order;
import in.trippinOnLens.oms.models.Product;
import in.trippinOnLens.oms.models.RequestModel;
import in.trippinOnLens.oms.services.inf.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author trippinOnCode
 */

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public int addProduct(RequestModel request) {
        
//        Product product =  productDao.getProductDetail(request.getProductId());
        
        
        int orderId = productDao.checkOrderForId(request.getUserId());
        int resp=0;
        if(orderId<=0){
            resp=productDao.createOrderAndInsert(request.getProductId(), request.getUserId(),request.getQty());
            orderId = productDao.checkOrderForId(request.getUserId());
        }
        resp= productDao.insertOrderDetail(request.getProductId(),orderId,request.getQty());
        return resp;
    }
    
    
    public List<Product> getAllProductForCart(RequestModel request){
    
        List<Product> products;
        
        products=productDao.getAllProductForOrderForUserId(request.getUserId());
        
        return products;
        
        
    }
    
    public List<Product> getAllProduct(){
       List<Product> products;
        
        products=productDao.getAllProduct();
        
        return products;
    }
    
    
    public List<Order> getAllOrders(){
        List<Order> orders;
        
        orders=productDao.getAllOrders();
        
        return orders;
    }
    
    @Override
    public List<Order> getSingleOrder(String orderId){
         List<Order> orders;
        
        orders=productDao.getSingleOrder(orderId);
        
        return orders;
    }
    
    @Override
    public int completOrder(){
      int resp= productDao.completeOrder();
      
      return resp;
    }
}
