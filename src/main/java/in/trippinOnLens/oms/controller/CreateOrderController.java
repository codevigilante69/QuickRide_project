/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.trippinOnLens.oms.controller;

import in.trippinOnLens.oms.models.Order;
import in.trippinOnLens.oms.models.Product;
import in.trippinOnLens.oms.models.RequestModel;
import in.trippinOnLens.oms.services.ProductServiceImpl;
import in.trippinOnLens.oms.services.inf.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TrippinOnCode
 */

@RestController
@RequestMapping("/orders")
public class CreateOrderController {
    
    @Autowired
    ProductService productService;
    @CrossOrigin(origins = "*")
    @PostMapping("/add_product")
    public ResponseEntity addProduct(@RequestBody RequestModel request ){
        
        
        try{
            
      
           int resp = productService.addProduct(request);
           if(resp==0){
               return new ResponseEntity(0,HttpStatus.OK);
           }
           else{
               return new ResponseEntity(0,HttpStatus.OK);
           }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
        }        
        
        
        
    }
    @CrossOrigin(origins = "*") 
    @GetMapping("/get_product_list")
    public ResponseEntity getProduct(@RequestBody RequestModel request ){
        
        List<Product> products;
        try{
            products = productService.getAllProductForCart(request);
           if(products != null){
               return new ResponseEntity(products,HttpStatus.OK);
           }
           else{
               return new ResponseEntity("no product in cart",HttpStatus.OK);
           }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
        }        
        
    }
    
    @CrossOrigin(origins = "*") 
    @GetMapping("/get_all_products")
    public ResponseEntity getProduct(){
        
        List<Product> products;
        try{
            products = productService.getAllProduct();
           if(products != null){
               return new ResponseEntity(products,HttpStatus.OK);
           }
           else{
               return new ResponseEntity("no product in cart",HttpStatus.OK);
           }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
        } 
    }
    
    
    @CrossOrigin(origins = "*") 
    @GetMapping("/get_all_orders")
    public ResponseEntity getOrders(){
        
        List<Order> orders;
        try{
            orders = productService.getAllOrders();
           if(orders != null){
               return new ResponseEntity(orders,HttpStatus.OK);
           }
           else{
               return new ResponseEntity("no product in cart",HttpStatus.OK);
           }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
        } 
    }
    
    
    @CrossOrigin(origins = "*") 
    @GetMapping("/get_single_order")
    public ResponseEntity getSingleOrder(@RequestParam String orderId){
        
        List<Order> orders;
        try{
            orders = productService.getSingleOrder(orderId);
           if(orders != null){
               return new ResponseEntity(orders,HttpStatus.OK);
           }
           else{
               return new ResponseEntity("no product in cart",HttpStatus.OK);
           }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
        } 
    }
    @CrossOrigin(origins = "*") 
    @GetMapping("/complete_order")
    public ResponseEntity completeOrder(){
        
        int resp=0;
        try{
            resp=productService.completOrder();
           if(resp > 0){
               return new ResponseEntity(0,HttpStatus.OK);
           }
           else{
               return new ResponseEntity("fail",HttpStatus.OK);
           }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
        } 
    }
    
}
