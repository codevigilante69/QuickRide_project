/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.trippinOnLens.oms.dao;

import in.trippinOnLens.oms.dao.inf.ProductDao;
import in.trippinOnLens.oms.models.Order;
import in.trippinOnLens.oms.models.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.ResultSetExtractor;
/**
 *
 * @author TrippinOnCode
 */

@Repository
public class ProductDaoImpl implements ProductDao{
    
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public Product getProductDetail(String productId) {
        
        System.out.println("productId"+productId);
       
        String query="select * from oms.product where id = "+productId;
        
        Product product = jdbcTemplate.query(query, new ResultSetExtractor<Product>() {
            @Override
            public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
                Product product=new Product();
                rs.next();
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setImgUrl(rs.getString("image_url"));
                product.setId(rs.getString("id"));
                product.setQty(rs.getString("qty_left"));
                
                return product;
            }
        });
        
        return product;
    }

    @Override
    public int checkOrderForId(String userId) {
        
        String query="select id from oms.order where user_id='"+userId+"' and status=0";
        
        int status=0;
        try{
            status = jdbcTemplate.queryForObject(query, Integer.class);
        }
        catch(Exception e ){
        }
        
        return status;
        
    }
    
    @Override
    public int insertOrderDetail(String productId, int orderId,int qty){
        
        String query="INSERT INTO oms.order_detail\n" +
                    "(order_id, product_id, qty)\n" +
                    "VALUES('"+orderId+"' , '"+productId+"', "+qty+"); ";
        int resp=0;
        try{
            jdbcTemplate.update(query);
            resp=1;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public int createOrderAndInsert(String productId, String userId,int qty){
        
        String query="INSERT INTO oms.order(user_id, status) VALUES("+userId+" , 0)";
        
        
        int resp=0;
        try{
            jdbcTemplate.update(query);
            resp=1;
        }
        catch(Exception e ){
            e.printStackTrace();
        }
        return resp;
    
    }
    
    
    public List<Product> getAllProductForOrderForUserId(String userId){
    
        String query="select a.* from oms.product a\n" +
                    "inner join oms.order_detail b on a.id=b.product_id \n" +
                    "inner join oms.order c on b.order_id =c.id\n" +
                    "where c.user_id = "+userId;
        
        List<Product> products = jdbcTemplate.query(query, new ResultSetExtractor<List<Product>>() {
            @Override
            public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
                
                List<Product> products = new ArrayList();
                
                while(rs.next()){
                    Product product= new Product();
                    product.setId(rs.getString("id"));
                    product.setImgUrl(rs.getString("image_url"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getString("price"));
                    product.setQty(rs.getString("qty_left"));
                    
                    products.add(product);
                }
                return products;
                
            }
        });
        
        return products;
    }
 
    
    public List<Product> getAllProduct(){
        
        String query = "select * from oms.product";
        
        List<Product> products = jdbcTemplate.query(query, new ResultSetExtractor<List<Product>>() {
            @Override
            public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
                
                List<Product> products = new ArrayList();
                
                while(rs.next()){
                    Product product= new Product();
                    product.setId(rs.getString("id"));
                    product.setImgUrl(rs.getString("image_url"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getString("price"));
                    product.setQty(rs.getString("qty_left"));
                    
                    products.add(product);
                }
                return products;
                
            }
        });
        
        return products;
    }
    
    @Override
    public List<Order> getAllOrders(){
        
        String query="select a.id as order_id, b.qty as qty_added ,c.* from oms.order a\n" +
                    "inner join oms.order_detail b \n" +
                    "on a.id =b.order_id\n" +
                    "inner join oms.product c\n" +
                    "on c.id =b.product_id \n" +
                    "where user_id=1";
        List<Order> orders;
         orders=jdbcTemplate.query(query, new ResultSetExtractor<List<Order>>() {
            @Override
            public List<Order> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Order> orders = new ArrayList();
                
                while(rs.next()){
                    Order order= new Order();
                    order.setImgUrl(rs.getString("image_url"));
                    order.setName(rs.getString("name"));
                    order.setOrderId(rs.getString("order_id"));
                    order.setPrice(rs.getString("price"));
                    order.setProductId(rs.getString("id"));
                    order.setQtyAdded(rs.getString("qty_added"));
                    
                    orders.add(order);
                }
                return orders;
            }
         });
         
         return orders;
    }
    
    public List<Order> getSingleOrder(String orderId){
    
        String query="select a.order_id, a.qty as qty_added,c.* from oms.order_detail a\n" +
                    "inner join oms.order b\n" +
                    "on a.order_id =b.id \n" +
                    "inner join oms.product c\n" +
                    "on c.id =a.product_id \n" +
                    "where order_id = "+orderId;
        
         List<Order> orders;
         orders=jdbcTemplate.query(query, new ResultSetExtractor<List<Order>>() {
            @Override
            public List<Order> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Order> orders = new ArrayList();
                
                while(rs.next()){
                    Order order= new Order();
                    order.setImgUrl(rs.getString("image_url"));
                    order.setName(rs.getString("name"));
                    order.setOrderId(rs.getString("order_id"));
                    order.setPrice(rs.getString("price"));
                    order.setProductId(rs.getString("id"));
                    order.setQtyAdded(rs.getString("qty_added"));
                    
                    orders.add(order);
                }
                return orders;
            }
         });
         
         return orders;
        
        
    }
    
    
    public int completeOrder(){
        int resp=0;
        
        String query="update oms.order set status=1 where status=0 limit 1";
        
        try{
            jdbcTemplate.update(query);
            resp=1;
        }
        catch(Exception e){
        e.printStackTrace();
        }
        return resp;
    }
}
